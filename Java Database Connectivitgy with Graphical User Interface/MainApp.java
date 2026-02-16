import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class MainApp extends Application {

    private TableView<Birthday> table = new TableView<>();
    private TextField nameField = new TextField();
    private DatePicker datePicker = new DatePicker();
    private TextField searchField = new TextField();

    @Override
    public void start(Stage stage) {

        stage.setTitle("জন্মদিন ব্যবস্থাপনা সিস্টেম");

        TableColumn<Birthday, String> nameCol =
                new TableColumn<>("নাম");

        nameCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getName()));

        TableColumn<Birthday, String> dateCol =
                new TableColumn<>("জন্মতারিখ");

        dateCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getBirthdate().toString()));

        table.getColumns().addAll(nameCol, dateCol);

        Button addBtn = new Button("যোগ করুন");
        Button updateBtn = new Button("আপডেট করুন");
        Button deleteBtn = new Button("মুছুন");
        Button searchBtn = new Button("খুঁজুন");
        Button upcomingBtn = new Button("আসন্ন জন্মদিন");

        addBtn.setOnAction(e -> addBirthday());
        updateBtn.setOnAction(e -> updateBirthday());
        deleteBtn.setOnAction(e -> deleteBirthday());
        searchBtn.setOnAction(e -> searchBirthday());
        upcomingBtn.setOnAction(e -> loadUpcoming());

        checkTodayBirthday();

        VBox root = new VBox(10,
                new Label("নাম লিখুন:"), nameField,
                new Label("জন্মতারিখ নির্বাচন করুন:"), datePicker,
                addBtn, updateBtn, deleteBtn,
                new Label("খুঁজুন (নাম/মাস):"), searchField,
                searchBtn, upcomingBtn,
                table);

        stage.setScene(new Scene(root, 500, 600));
        stage.show();

        loadAll();
    }

    private void addBirthday() {
        try (Connection con = DBConnection.getConnection()) {

            String sql = "INSERT INTO birthdays(name, birthdate) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameField.getText());
            ps.setDate(2, Date.valueOf(datePicker.getValue()));
            ps.executeUpdate();

            loadAll();

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void updateBirthday() {
        Birthday selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE birthdays SET name=?, birthdate=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameField.getText());
            ps.setDate(2, Date.valueOf(datePicker.getValue()));
            ps.setInt(3, selected.getId());
            ps.executeUpdate();

            loadAll();

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void deleteBirthday() {
        Birthday selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM birthdays WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selected.getId());
            ps.executeUpdate();

            loadAll();

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void searchBirthday() {
        try (Connection con = DBConnection.getConnection()) {

            String text = searchField.getText();

            String sql = "SELECT * FROM birthdays WHERE name LIKE ? OR MONTH(birthdate)=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");

            int month = 0;
            try { month = Integer.parseInt(text); } catch (Exception ignored) {}
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();

            ObservableList<Birthday> list = FXCollections.observableArrayList();

            while(rs.next()) {
                list.add(new Birthday(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate").toLocalDate()));
            }

            table.setItems(list);

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadUpcoming() {
        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM birthdays ORDER BY MONTH(birthdate), DAY(birthdate)";
            ResultSet rs = con.createStatement().executeQuery(sql);

            ObservableList<Birthday> list = FXCollections.observableArrayList();

            while(rs.next()) {
                list.add(new Birthday(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate").toLocalDate()));
            }

            table.setItems(list);

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadAll() {
        try (Connection con = DBConnection.getConnection()) {

            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT * FROM birthdays");

            ObservableList<Birthday> list = FXCollections.observableArrayList();

            while(rs.next()) {
                list.add(new Birthday(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate").toLocalDate()));
            }

            table.setItems(list);

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void checkTodayBirthday() {
        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT name FROM birthdays WHERE MONTH(birthdate)=? AND DAY(birthdate)=?";
            PreparedStatement ps = con.prepareStatement(sql);

            LocalDate today = LocalDate.now();
            ps.setInt(1, today.getMonthValue());
            ps.setInt(2, today.getDayOfMonth());

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("আজকের জন্মদিন");
                alert.setHeaderText(null);
                alert.setContentText("আজ " + rs.getString("name") + " এর জন্মদিন!");
                alert.show();
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        launch();
    }
}

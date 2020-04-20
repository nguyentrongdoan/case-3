package dao;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    private String jdbcURL ="jdbc:mysql://localhost:3306/employee_customer_data";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Welcome_0692";

    private static final String INSERT_EMPLOYEES_SQL = "insert into employees" +
            "(name , birthday, phoneNumber, email, address)VALUES"
            + "(?, ?, ?, ?, ?); ";
    private static final String SELECT_EMPLOYEE_BY_ID = "select id, name, birthday, phoneNumber, email, address from employees where id = ?";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String DELETE_EMPLOYEES_SQL = "delete from employees where id = ?;";
    private static final String UPDATE_EMPLOYEES_SQL = "update employees set  name =?, birthday = ?, phoneNumber = ?, email = ?, address = ? where id = ?;";

    public EmployeeDAO(){}


    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        System.out.println(INSERT_EMPLOYEES_SQL);
        try(Connection connection = getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthday());
            preparedStatement.setString(3, employee.getPhoneNumber());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getAddress());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Employee selectEmployee(int id) {
        Employee employee = null;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                employee = new Employee(id, name, birthday, phoneNumber, email, address);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> selectAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                employees.add(new Employee(id, name, birthday, phoneNumber, email, address));
            }

        }catch (SQLException e){
            printSQLException(e);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEES_SQL);) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdate;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL);){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthday());
            preparedStatement.setString(3, employee.getPhoneNumber());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getAddress());
            preparedStatement.setLong(6, employee.getId());

            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
    private void printSQLException(SQLException ex){
        for (Throwable e: ex){
            if (e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState:" + ((SQLException)e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null){
                    System.out.println("Cause:" + t);
                    t = t.getCause();
                }
            }
        }
    }
}

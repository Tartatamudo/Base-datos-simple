import java.sql.*;
import java.util.Scanner;
public class Clase1 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/empleados";
    static final String USER = "root";
    static final String PASS = "";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            Scanner scanner = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("----- Menú -----");
                System.out.println("1. Ver datos");
                System.out.println("2.Agregar Empleado");
                System.out.println("3. Modificar empleado");
                System.out.println("4.Eliminar empleado");
                System.out.println("5. Salir");
                System.out.print("Ingrese la opción: ");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        verDatos(stmt);
                        break;
                    case 2:
                        AgregarEmpleado(stmt);
                        break;
                    case 3:
                        modificarEmpleado(stmt);
                        break;
                    case 4:
                        eliminarEmpleado(stmt);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Opción no válida.Intente de nuevo.");
                }
            } while (opcion != 5);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Adiós!");
    }
    private static void verDatos(Statement stmt) throws
            SQLException {
        String sql = "SELECT * FROM empleado";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            int codigo = rs.getInt("codigo");
            String apellido1 = rs.getString("apellido1");
            String apellido2 = rs.getString("apellido2");
            String nif = rs.getString("nif");
            int departamento = rs.getInt("codigo_departamento");
            System.out.println("Código: " + codigo + ", nombre: " +
                    nombre + ", apellido1: " + apellido1
                    + ", apellido2:" + apellido2 + ", nif:" + nif +
                    ", codigo departamento:" + departamento);
        }
        rs.close();
    }
    private static void AgregarEmpleado(Statement stmt) throws
            SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese codigo incremental");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nombre empleado");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese primer apellido");
        String apellido1 = scanner.nextLine();
        System.out.println("Ingrese segundo apellido");
        String apellido2 = scanner.nextLine();
        System.out.println("Ingrese nif");
        String nif = scanner.nextLine();
        System.out.println("Ingrese codigo departamento");
        int departamento = scanner.nextInt();
        scanner.nextLine();
        String sql = "Insert INTO empleado Values ("+ codigo + ", '" + nif + "', '" + nombre + "', '" +
        apellido1 + "', '" + apellido2 +"', " +
                departamento + ")";
        stmt.executeUpdate(sql);
        System.out.println("Empleado añadido correctamente.");
    }
    private static void eliminarEmpleado(Statement stmt) throws
            SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el id del empleado a eliminar: ");
                String idEliminar = scanner.nextLine();
        String sql = "DELETE FROM empleado WHERE codigo = '" +
                idEliminar + "'";
        stmt.executeUpdate(sql);
        System.out.println("Empleado eliminado correctamente.");
    }
    private static void modificarEmpleado(Statement stmt) throws
            SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del empeleado que desea modificar: ");
        String nombreBuscar = scanner.nextLine();
        System.out.println("Ingrese nuevo nombre empleado");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese primer apellido");
        String apellido1 = scanner.nextLine();
        System.out.println("Ingrese segundo apellido");
        String apellido2 = scanner.nextLine();
        System.out.println("Ingrese nuevo nif");
        String nif = scanner.nextLine();
        System.out.println("Ingrese nuevo codigo departamento");
        int departamento = scanner.nextInt();
        String sql = "UPDATE empleado SET nombre = '"+ nombre + "', apellido1= '" + apellido1 + "', apellido2 = '" + apellido2 +"', nif = '" + nif + "', codigo_departamento = " + departamento +
        " WHERE nombre = '" + nombreBuscar + "'";
        stmt.executeUpdate(sql);
        System.out.println("Empleado modificado correctamente.");
    }
}
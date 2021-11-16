import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //Conexion a DB

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "1234";
        String getAll = "select * from usuarios";

        try {
            // conectamos con la base de datos
            Connection mysql = DriverManager.getConnection(url + "bank", user, password);

            // creamos una statement sql a la base de datos (sentencia / consulta)
            Statement dbData = mysql.createStatement();

            //Ejecutamos el statement
            ResultSet result = dbData.executeQuery(getAll);

            //Mostrar los datos que recupera la consulta
            while(result.next()){
                System.out.print(result.getString("name") + " - ");
                System.out.print(result.getString("phone") + " - ");
                System.out.println(result.getString("email") + "/n");
            }

        } catch (SQLException err) {           // por si no encuentra la DB, tiene un catch
            err.printStackTrace();
        }



    }
}

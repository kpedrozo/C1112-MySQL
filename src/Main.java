import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //Conexion a DB

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "1234";

        //Consulta general
        String getAll = "select * from usuarios";

        //Update con id definido
        String updateOne = "UPDATE usuarios SET name='Juan' WHERE  id=1";
        //Update pasando parametros cuando se llama a la sentencia
        String updatePrepared = "UPDATE usuarios SET name=? WHERE id=?";
        //Consulta pasando parametros cuando se llama a la sentencia
        String getAllPrepared = "select * from usuarios WHERE id=?";

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
                System.out.println(result.getString("email"));
            }
            System.out.println("                                                ");
            System.out.println("------------------------------------------------");
            System.out.println("                                                ");

            //Actualizar un registro
            dbData.executeUpdate(updateOne);

            //Ejecutar de nuevo el statement consulta de datos
            result = dbData.executeQuery(getAll);

            while(result.next()){
                System.out.print(result.getString("name") + " - ");
                System.out.print(result.getString("phone") + " - ");
                System.out.println(result.getString("email"));
            }
            System.out.println("                                                ");
            System.out.println("------------------------------------------------");
            System.out.println("                                                ");

            PreparedStatement dbDataPrepared = mysql.prepareStatement(updatePrepared);
            dbDataPrepared.setString(1,"Marcos");
            dbDataPrepared.setInt(2,2);
            dbDataPrepared.executeUpdate();

            result = dbData.executeQuery(getAll);

            while(result.next()){
                System.out.print(result.getString("name") + " - ");
                System.out.print(result.getString("phone") + " - ");
                System.out.println(result.getString("email"));
            }
            System.out.println("                                                ");
            System.out.println("------------------------------------------------");
            System.out.println("                                                ");

            dbDataPrepared = mysql.prepareStatement(getAllPrepared);
            dbDataPrepared.setInt(1,3);
            result = dbDataPrepared.executeQuery();

            if(result.next()){
                System.out.print(result.getString("name") + " - ");
                System.out.print(result.getString("phone") + " - ");
                System.out.println(result.getString("email"));
            }
            System.out.println("                                                ");
            System.out.println("------------------------------------------------");
            System.out.println("                                                ");

        } catch (SQLException err) {           // por si no encuentra la DB, tiene un catch
            err.printStackTrace();
        }







    }
}

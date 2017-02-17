package connexion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;


//import javax.*;
public class Connexion{
    public Connection con = null;
    String requete = "";
    private static void affiche(String message) 
    {
	System.out.println(message);
    }
    private static void arret(String message) 
    {
		System.err.println(message);
    }
    public void fermer() throws Exception
    {
	if(con!=null){
            con.close();
            }
    }
    public void ConnectionDB(){
        try{
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://localhost:5432/projetgit";
            String user="postgres";
            String password="itu";
            con=DriverManager.getConnection(url,user,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
    public int getTaille(String rq) throws SQLException{
        Connexion c = new Connexion();
        c.ConnectionDB();
        Statement stmt = c.con.createStatement();
        ResultSet resultats = stmt.executeQuery(rq);
        ResultSetMetaData rsmd = resultats.getMetaData();
        int g = 0;
        resultats.next();
        g = resultats.getInt(1);
        resultats.close();
        return g;
    }
    
    public void update(String requete)
    {
    	try{
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate(requete);
		}catch (SQLException e) {
			arret(e.getMessage());
		}
    }
    public String createUpdateStatement(String nom,String nomCol,String valCol,String cond,String valcond){
	   String requete="UPDATE "+nom+" SET "+nomCol+" = '"+valCol+"' WHERE "+cond+"='"+valcond+"'";
		return requete;
	}
    public void drop(String requete)
    {
    	try{
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate(requete);
		}catch (SQLException e) {
			arret(e.getMessage());
		}
    }
    public String createRequete(String nom,Object object){
        Class c = object.getClass();
            Field[] m = c.getDeclaredFields();
            String requete="";
            int i=0;
            for( i= 0; i < m.length-1; i++)
            {
                requete=requete+m[i].getName();
                if(i!=m.length-2){
                    requete=requete+",";
                }
            }
            requete=requete+","+m[i].getName();
            return requete;
    }
    public int Taille(String requete){
        try{
                
    		Statement stmt = con.createStatement();
    		ResultSet resultats = stmt.executeQuery(requete);
                int i=0;
		while (resultats.next()) 
                {
                   
                   i++;
                }
                return i;
        }
        catch(Exception e){
            arret(e.getMessage());
            
        }
       return -1;
    }
    public String createInsertStatement(String nom,Object object)
    {
            Class c = object.getClass();
            Field[] m = c.getDeclaredFields();
            String requete="insert into "+nom+" ("+createRequete(nom,object);
            requete=requete+") values "+object.toString();
            return requete;
    }
    public String createSelectStatement(String nom,Object object)
    {
            Class c = object.getClass();
            Field[] m = c.getDeclaredFields();
            String requete="select "+createRequete(nom,object)+"  from "+nom;
            return requete;
    }
	public String createSelectStatementWithLimit(String nom,Object object,int d,int f)
    {
            Class c = object.getClass();
            Field[] m = c.getDeclaredFields();
            String requete="select "+createRequete(nom,object)+" from "+nom+" limit "+String.valueOf(f)+" offset "+String.valueOf(f);
            return requete;
    }
    public String createSelectCondition(String[] condition,String[] values,String nom,Object object)
    {
            Class c = object.getClass();
            Field[] m = c.getDeclaredFields();
            String requete="select "+createRequete(nom,object)+" from "+nom+" where ( ";
            if(condition.length==values.length){
                int i=0;
                for(i=0;i<condition.length-1;i++)
                {
                    requete=requete+condition[i]+"='"+values[i]+"' AND ";
                }
                requete=requete+condition[i]+"='"+values[i]+"')";
                 return requete;
            }
            else{
                return null;
            }
    }
	public String createSelectConditionWithOr(String[] condition,String[] values,String nom,Object object)
    {
            Class c = object.getClass();
            Field[] m = c.getDeclaredFields();
            String requete="select "+createRequete(nom,object)+" from "+nom+" where ( ";
            if(condition.length==values.length){
                int i=0;
                for(i=0;i<condition.length-1;i++)
                {
                    requete=requete+condition[i]+" LIKE '"+values[i]+"' OR ";
                }
                requete=requete+condition[i]+" LIKE '"+values[i]+"')";
                 return requete;
            }
            else{
                return null;
            }
    }
    public boolean Insert(String requete)
    {
         try{
            ResultSet resultats = null;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(requete);
            String comm="commit";
            resultats=stmt.executeQuery(comm);
             }
            catch(Exception e){

            
        }
	return true;
    }
    public Object[] Select(String requete,Object object,String nom) throws ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException{
        int taille=Taille(requete);
        if(taille>0){
            Object[] liste=new Object[taille];
            Class cl = Class.forName(object.getClass().getName());
            Field[] m = cl.getDeclaredFields();
            Statement stmt = con.createStatement();
            ResultSet resultats = stmt.executeQuery(requete);
            ResultSetMetaData rsmd = resultats.getMetaData();
            int nbCols=rsmd.getColumnCount();
            int u=0;
           
            while (resultats.next()) 
                {
                    int j=0;
                   liste[u]=cl.newInstance();
                   
                   for(int i=1;i<=nbCols;i++)
                   {
                        String meth="set"+m[j].getName();
                        Method method=cl.getMethod(meth, String.class);
                        method.invoke(liste[u], resultats.getString(i));
                       
                        j++;
                   }
                   u++;
                   
                }
                resultats.close();
                return liste;
        }
        return null;
    }
    public int getLastId(String table,String nomColonne)
    {
        try{
                
    		Statement stmt = con.createStatement();
    		ResultSet resultats = stmt.executeQuery("select max("+nomColonne+") from "+table);
    		ResultSetMetaData rsmd = resultats.getMetaData();
		int nbCols = rsmd.getColumnCount();
		boolean next = resultats.next();
		while (next) 
                {
                   
                    int i=Integer.parseInt(resultats.getString(1));
                    resultats.close();
                    return i;
                }
        }
        catch(Exception e){
            arret(e.getMessage());
             
            
        }
        return 0;
      
    }
    public Object GetClass(String nom) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class cl = Class.forName(nom);
        Object o = cl.newInstance();
        return o;
    }
   
} 
import java.util.*;
import org.sql2o;

public class Endangered extends Aniamls{

public static final String NONEENDANGERED_TYPE;

public Endangered(String name,String type){
	this.name = name;
	this.type= NONEENDANGERED_TYPE;
}

public String getType(){
	return type;
}



@override
public static List<NoneEndangered> all(){
	String sql = "SELECT * FROM animals WHERE type='type';";
	try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .exexcuteAndFetch(NoneEndangered.class);
	}
}

@override
public static NoneEndangered find(int id){
try(Connection con = DB.sql2o.open()){
   String sql = "SELECT * From animals WHERE id=:id";
   NoneEndangered endangered = con.createQuery(sql)
   .addParameter("id",id)
   .throwOnMappingFailure(false)
   .exexcuteAndFetchFirdt(NoneEndangered.class);
}
}
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

}
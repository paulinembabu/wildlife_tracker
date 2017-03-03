import java.util.*;
import org.sql2o.*;
import java.sql.Timestamp;

public class Sightings(){
	private int animalsId;
	private String location;
	private String rangerName;
	private int id;
	private  Timestamp timeSeen;
 
 public  Sightings (int animalsId, String location,String rangerName){
 	this.rangerName=rangerName;
 	this.animalsId=animalsId;
 	this.location=location;
 }


 public int getId (){
 	return id;
 }
 public int getAnimalsId(){
 	return animalsId;
 }

 public String getLocation(){
 	return location;
 }
 
 public String getRangerName(){
 	return rangerName;
 }

 public Timestamp getTimeSeen(){
 	return time
 }

 @Override
  public boolean equals(Object otherSightings){
    if (!(otherSightings instanceof Sightings)) {
      return false;
    } else {
      Animals newSightings = (Sightings) otherSightings;
        return this.getRangerName().equals(newSightings.getRangerName())&&
         this.getLocation().equals(newSightings.getLocation())&&
         this.getTimeSeen().equals(newSightings.getTimeSeen())&&
         this.getAnimalsId()==(newSightings.getAnimalsId())&&
         this.getId()==(newSightings.getId());
         
    }
  }

public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (rangername,location,timeSeen,animalsId) VALUES (:rangername,:location,:timeSeen,:animalsId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animalsId",this.animalsId)
        .addParameter("location",this.location)
        .addParameter("rangername", this.rangername)
        .addParameter("timeSeen", this.timeSeen)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM sightings WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
  
  public  static List<Animals> all(){
  	try (Connection con = DB.sql2o.open()){
  		String sql = "SELECT * FROM sightings";
      con.createQuery(sql)
      .executeAndFetch();

  	}

  	public static Animals find(int id){
  		try(Connection con =DB.sql2o.open()){
  			String sql = "SELECT * FROM sightings WHERE id=:id"
  			Sightings sighting = con.createQuery(sql)
  			.addParameter("id",id)
  			.executeAndFetchFirst(Sightings.class)
  			return sighting;
  		}
  	}

  	public void update (String rangerName,String location){
  		try(Connection con = DB.sql2o.open()){
  		String sql ="UPDATE sightings SET rangername=:rangername,location=:location WHERE id=:id";
  			con.createQuery(sql)
  			.addParameter("id",id)
  			.addParameter("rangername",rangername)
  			.addParameter("location",location)
  			.executeUpdate();
  		}
  	}
}
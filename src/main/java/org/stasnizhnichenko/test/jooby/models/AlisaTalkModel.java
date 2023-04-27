package org.stasnizhnichenko.test.jooby.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.naming.spi.DirStateFactory.Result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;

import org.sql2o.Connection;

//Response:
public class AlisaTalkModel extends CommonModel { 

		@JsonProperty(value = "version", required = true, defaultValue = "1.0")
	    private String version;
		@JsonProperty(value = "session", required = true)
	    private String session;
		@JsonProperty(value = "response", required = true)
	    private Response res;
		
	    // конструктор #1 -- используется для создания экземпляра из входящего запроса
	    @JsonCreator
	    public AlisaTalkModel(
	        @JsonProperty("version")    final String version,
	        @JsonProperty("session")    final String session,
	        @JsonProperty("response") 	final Response res
	    ) {
	    	super();
	    	 
	        this.version    = version;
	        this.session 	= session;
	        this.res     	= res;
	    }

	    public AlisaTalkModel( ) {
	    	
	    }
		
	    public final Response Greeting()  {
	    	
    		Response res = new Response();
    		
	    	try {
					this.findGreetings(1).forEach( txt -> { 
						res.setText(txt.getGreeting());
					});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	res.setEnd_session(false);
	    	
	    	return res;	    	
	    }

	    public final Response ByeBye()  {
	    	
	    	Response res = new Response();
	    	
	    	
	    	try {
				this.findGreetings(2).forEach( txt -> { 
					res.setText(txt.getGreeting());
				});
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	res.setEnd_session(true);
	    	
	        return res;
	    }
	    
	    
	    public AlisaTalkModel Talk(JsonNode sess)  {
	    	
	    	Response r = new Response();
	    	
	    	final Boolean flgSess = sess.path("session").path("new").asBoolean();
	    	final String original_utterance = sess.path("request").path("original_utterance").asText();
	    	session = sess.path("session").path("session_id").asText();
	    	version = "1.0";
	    	
	    	if (flgSess == true) {
	    		
	    		r = Greeting();
	    		
	    	} else {
	    		// сессия продолжается....
	    		
	    		// попрощаемся, если нам ответили так:
	    		if (original_utterance.contentEquals("пока") || 
	    			original_utterance.contentEquals("стоп") ||
	    			original_utterance.contentEquals("прощай") )
	    		{
					r = ByeBye();
	    			
	    		} else {
	    			r.setText("Я не поняла. Повторите, пожалуйста!");
	    			r.setEnd_session(false);
	    		}
	    		
	    		System.out.println("Алиса нам ответила : " + original_utterance);
	    		
	    	}
	    	
	    	res = r;
	    	
	        return (new AlisaTalkModel(version, session, res));
	    }
		

		public List<Greeting> findGreetings(final int id) throws SQLException {
	        Connection   con    = CommonModel.sql2o.open();
	        List<Greeting> result = con.createQuery(
	            "SELECT id, greeting FROM GREETINGS where typegreeting = :tg"
	        ).addParameter("tg", id)
       		.executeAndFetch(Greeting.class);
			con.close();
			
			//System.out.println("res = " + result.get(0).getGreeting().replaceAll(System.lineSeparator(), ""));
			
	        return result;
	    }
	    

	    
		
}

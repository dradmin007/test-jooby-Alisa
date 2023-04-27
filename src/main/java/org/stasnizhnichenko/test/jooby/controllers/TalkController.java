package org.stasnizhnichenko.test.jooby.controllers;


import io.jooby.Context;
import io.jooby.Route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.stasnizhnichenko.test.jooby.models.AlisaTalkModel;



public class TalkController extends CommonController {

    // Разговор с Алисой
    public final Route.Handler talk;
    
    
    public TalkController() {
        // запрос списка фильмов
        this.talk = (Context ctx) -> {
            // считываем номер страницы во входных параметрах
            ObjectMapper mapper = new ObjectMapper();
        	AlisaTalkModel myTalk = new AlisaTalkModel();
        	
            try {

            	JsonNode sess = mapper.readTree(ctx.body().value());
            	
            	myTalk.Talk( sess );
           	
            } catch (Exception exc) {
            	return ok();
//                return error(Code.INTERNAL_SERVER_ERROR, 
//                		     "Не удалось получить список фильмов по заданным параметрам!");
            }

            // возвращаем результат в обёртке списка
            return ok(new Wrapper(myTalk));
        };
    }
}

    


package org.stasnizhnichenko.test.jooby.controllers;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

// базовый абстрактный класс контроллера
public abstract class CommonController {
    // множество кодов ответов сервера

    // результат выполнения запроса
    @JsonInclude(Include.NON_NULL)
    protected static class Result {
    	@JsonUnwrapped
        public final Object data; // данные для ответа

        
        public Result(
                final Object data
            ) {
                this.data = data;
            }
    }
    
    // успешный ответ, данные есть
    public final Result ok(final Object data) {
        return new Result(data);
    }

    // успешный ответ, данных нет
    public final Result ok() {
        return this.ok(null);
    }

    // обертка для списка записей
    public final class Wrapper {
    	@JsonUnwrapped
        public final Object talk; // разговор

        // конструктор
        public Wrapper(
            final Object talk
        ) {
        	
        	this.talk = talk;
        }
    }
}

package com.example.demo.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.event.RecursoCriadoEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
         HttpServletResponse response = recursoCriadoEvent.getResponse();
         Long codigo = recursoCriadoEvent.getCodigo();

         addHeaderLocation(response, codigo);
    }

    private void addHeaderLocation(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}

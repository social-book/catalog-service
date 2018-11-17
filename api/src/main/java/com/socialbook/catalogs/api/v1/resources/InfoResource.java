package com.socialbook.catalogs.api.v1.resources;

import com.socialbook.catalogs.Info;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@RequestScoped
@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {

    @GET
    @Path("/info")
    public Response getInfo() {
        Info info = new Info();
        ArrayList<String> clani = new ArrayList<>();
        ArrayList<String> mikrostoritve = new ArrayList<>();
        ArrayList<String> github = new ArrayList<>();
        ArrayList<String> dockerhub = new ArrayList<>();
        info.setClani(clani);
        info.setDockerhub(dockerhub);
        info.setGithub(github);
        info.setMikrostoritve(mikrostoritve);
        info.setOpis_projekta("Don't care actually FYI");
        return Response.ok(info).build();
    }

}

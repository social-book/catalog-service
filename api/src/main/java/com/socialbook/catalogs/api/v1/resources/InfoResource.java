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

//Info resources requester... for info about requests
    @GET
    @Path("/info")
    public Response getInfo() {
        Info info = new Info();
        ArrayList<String> clani = new ArrayList<>();
        clani.add("nr4758");
        clani.add("ms4779");

        ArrayList<String> travis = new ArrayList<>();
        travis.add("https://travis-ci.org/social-book/catalog-service");
        travis.add("https://travis-ci.org/social-book/user-serivce");
        travis.add("https://travis-ci.org/social-book/upload-service");

        ArrayList<String> mikrostoritve = new ArrayList<>();
        mikrostoritve.add("http://159.122.186.89:31340/v1/users");
        mikrostoritve.add("http://159.122.186.89:31175/images?imageId=1");
        mikrostoritve.add("http://159.122.186.89:31777/v1/albums");

        ArrayList<String> github = new ArrayList<>();
        github.add("https://github.com/social-book");

        ArrayList<String> dockerhub = new ArrayList<>();
        dockerhub.add("https://hub.docker.com/r/40850473/service-upload/");
        dockerhub.add("https://hub.docker.com/r/40850473/service-users/");
        dockerhub.add("https://hub.docker.com/r/40850473/service-catalog/");

        info.setClani(clani);
        info.setDockerhub(dockerhub);
        info.setGithub(github);
        info.setMikrostoritve(mikrostoritve);
        info.setTravis(travis);
        info.setOpis_projekta("Naš projekt implementira funkcionalnosti socialnega omrežja, kjer uporabnik se lahko prijavi, objavla slike in všečka ter komentira druge slike. Imava na razpolago 6 neodvisnih (nekatere so v teku) mikrostoritev, tj. catalog service, user service, upload service, statistics service, like-comment service, frontend service. Catalog service upravlja vso logiko povezano z albumi (kateremu uporabniku pripada, kateri kategoriji ipd.), frontend je implementiran v angular 6 (v teku bova morda uporabila celo 7).");
        return Response.ok(info).build();
    }

}

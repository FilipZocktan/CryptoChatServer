/*
 * Copyright (c) Filip Zocktan Studios 2018. This Project is licenced under the GNU AGPL 3.0. You are allowed to modify, distribute and use commercially. You have to include the license and a copyright notice, state the changes, make your source code public and use the GNU AGPL 3.0 license too.
 */

package de.filipzocktan.cryptochat.server.web;

import spark.Request;
import spark.Response;

import static j2html.TagCreator.fileAsString;

public class WebPageBuilder {

    public static String getSite(String undersite, Request req, Response resp) {
        String renderedSite = fileAsString("/website/basesite.html").render();
        switch (undersite.toUpperCase()) {
            case "LOGIN":

                renderedSite = renderedSite.replaceAll("/CONTENT", fileAsString("/website/parts/forms/login_form.html").render());
                if (req.queryParams().contains("wrongpw")) {
                    renderedSite = renderedSite.replaceAll("ALERT", fileAsString("/website/parts/dialogues/wrong_password.html").render());
                }
                if (req.queryParams().contains("wronguser")) {
                    renderedSite = renderedSite.replaceAll("ALERT", fileAsString("/website/parts/dialogues/wrong_username.html").render());
                }
                renderedSite = renderedSite.replaceAll("ALERT", "");

                break;
            case "REGISTER":

                renderedSite = renderedSite.replaceAll("/CONTENT", fileAsString("/website/parts/forms/register_form.html").render());
                if (req.queryParams().contains("pwnotequal")) {
                    renderedSite = renderedSite.replaceAll("ALERT", fileAsString("/website/parts/dialogues/wrong_password.html").render());
                }
                if (req.queryParams().contains("userexists")) {
                    renderedSite = renderedSite.replaceAll("ALERT", fileAsString("/website/parts/dialogues/wrong_username.html").render());
                }
                renderedSite = renderedSite.replaceAll("ALERT", "");

                break;
            case "INDEXIN":
                renderedSite = renderedSite.replaceAll("/CONTENT", "You're logged in.");
                break;
            case "INDEXOUT":
                renderedSite = renderedSite.replaceAll("/CONTENT", "You're not logged in.");
                break;
            case "LOGOUT":
                renderedSite = renderedSite.replaceAll("HEADCONTENT", "<meta http-equiv=\"refresh\" content=\"5; /\">");
                renderedSite = renderedSite.replaceAll("/CONTENT", fileAsString("/website/parts/logout.html").render());
                break;
        }
        renderedSite = renderedSite.replaceAll("/CONTENT", "");
        renderedSite = renderedSite.replaceAll("HEADCONTENT", "");
        return renderedSite;
    }

}

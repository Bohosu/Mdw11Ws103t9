/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import data.Player;
import data.PlayerDAO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author bohosu
 */
@Path("/players")
public class Players {

    @GET
    @Produces("application/xml")
    public String getPlayers() {
        PlayerDAO dao = new PlayerDAO();
        String r = "";
        r = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";
        r += "<players>\r\n";
        for(Player p : dao.getAll()) {
            r += "\t<player id=\"" + p.getId().toString() + "\">\r\n";
            r += "\t\t<name>" + p.getName() + "</name>\r\n";
            r += "\t\t<surname>" + p.getSurname() + "</surname>\r\n";
            r += "\t\t<number>" + p.getNumber() + "</number>\r\n";
            r += "\t</player>\r\n";
        }
        r += "</players>";
        return r;
    }

}

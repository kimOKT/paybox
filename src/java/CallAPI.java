/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


import javax.servlet.RequestDispatcher;


public class CallAPI extends HttpServlet {
    
    public static String error;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        try
        {
            String montant = request.getParameter("MONTANT");
            if(this.getResponseFromAPIPaybox(montant)){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/success.jsp");
                requestDispatcher.forward(request, response);
            }
            else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        catch(Exception e){
            request.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
    
    public boolean getResponseFromAPIPaybox(String montant) throws Exception{
        try{
            boolean response = true;
            // Compte de test 
            DateManager dateManager = new DateManager();
            NumQuestionManager numManager = new NumQuestionManager();

            String site = "1999888";
            String dateQ = dateManager.getDateActuel();
            String type = "00001";
            String numQuestion = numManager.getNumQuestion();
            String rang = "32";
            String identifiant = "107904482";
            String cle = "1999888I"; 
            String devise = "978";
            String reference = "Hello word";
            String version = "00104";
            String porteur = "1111222233334444";
            String dateval = "0318";
            String cvv = "123";
            String activite = "024";
            String archivage = "AXZ130968CT2";
            String differe = "000";
            String numAppel = "";
            String numtrans = "";
            String autorisation = "";
            String pays = "";

            String url = this.getUrl(dateQ,type,numQuestion,montant,site,rang,
                    identifiant,cle,devise,reference,version,porteur,
                    dateval,cvv,archivage,differe,numAppel,numtrans,
                    autorisation,pays);
            
            url = "https://preprod-ppps.paybox.com/PPPS.php" + url;
            System.out.println(url);
            
            Client client = Client.create();
            WebResource webResource2 = client.resource(url);
            ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
            if (response2.getStatus() != 200) {
                response = false;
                throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
            }
            String output2 = response2.getEntity(String.class);
            System.out.println(output2);
            
            return response;
        }
        catch(Exception exception){
            throw exception;
        }
    }
    
    public String getUrl(String dateQ,String type,String numQuestion,String montant,String site,String rang,
            String identifiant,String cle,String devise, String reference,String version,String porteur,
            String dateval,String cvv,String archivage,String differe,String numappel,String numtrans,
            String autorisation,String pays){
        
        String lastUrl = "";
        lastUrl += "?DATEQ="+dateQ;
        lastUrl += "&TYPE="+type;
        lastUrl += "&NUMQUESTION="+numQuestion;
        lastUrl += "&MONTANT="+montant;
        lastUrl += "&SITE="+site;
        lastUrl += "&RANG="+rang;
        lastUrl += "&IDENTIFIANT="+identifiant;
        lastUrl += "&CLE="+cle;
        lastUrl += "&DEVISE="+devise;
        lastUrl += "&REFERENCE="+this.formatReference(reference);
        lastUrl += "&VERSION="+version;
        lastUrl += "&PORTEUR="+porteur;
        lastUrl += "&DATEVAL="+dateval;
        lastUrl += "&CVV="+cvv;
        lastUrl += "&ARCHIVAGE="+archivage;
        lastUrl += "&DIFFERE="+differe;
        lastUrl += "&NUMAPPEL="+numappel;
        lastUrl += "&NUMTRANS="+numtrans;
        lastUrl += "&AUTORISATION="+autorisation;
        lastUrl += "&PAYS="+pays;
        
        return lastUrl;
    }
    
    String formatReference(String reference){
        reference = reference.replaceAll("\\s","+");
        return reference;
    }
}

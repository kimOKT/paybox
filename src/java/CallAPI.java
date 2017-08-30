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
import com.sun.jersey.api.representation.Form;


import javax.servlet.RequestDispatcher;
import javax.ws.rs.core.MediaType;


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
            
            DateManager dateManager = new DateManager();

            String site = "1999888";
            String dateQ = dateManager.getDateActuel();
            String type = "00001";
            String numQuestion = NumQuestionManager.getNumQuestion();
            System.out.println(numQuestion);
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

            Form form = this.getForm(dateQ,type,numQuestion,montant,site,rang,
                    identifiant,cle,devise,reference,version,porteur,
                    dateval,cvv,archivage,differe,numAppel,numtrans,
                    autorisation,pays);
            
            String baseUrl = "https://preprod-ppps.paybox.com/PPPS.php";
            Client client = Client.create();
            WebResource webResource2 = client.resource(baseUrl);
            ClientResponse response2 = webResource2.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, form);
            
            if (response2.getStatus() != 200) {
                response = false;
                throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
            }
            response2.bufferEntity();
            String output2 = response2.getEntity(String.class);
            System.out.println(output2);
            response = this.analyseReponse(output2);
            
            return response;
        }
        catch(Exception exception){
            throw exception;
        }
    }
    
    public Form getForm(String dateQ,String type,String numQuestion,String montant,String site,String rang,
            String identifiant,String cle,String devise, String reference,String version,String porteur,
            String dateval,String cvv,String archivage,String differe,String numappel,String numtrans,
            String autorisation,String pays){
        
        Form form = new Form();
        form.add("DATEQ",dateQ);
        form.add("TYPE",type);
        form.add("NUMQUESTION",numQuestion);
        form.add("MONTANT",montant);
        form.add("SITE",site);
        form.add("RANG",rang);
        form.add("IDENTIFIANT",identifiant);
        form.add("CLE",cle);
        form.add("DEVISE",devise);
        form.add("REFERENCE",this.formatReference(reference));
        form.add("VERSION",version);
        form.add("PORTEUR",porteur);
        form.add("DATEVAL",dateval);
        form.add("CVV",cvv);
        form.add("ARCHIVAGE",archivage);
        form.add("DIFFERE",differe);
        form.add("NUMAPPEL",numappel);
        form.add("NUMTRANS",numtrans);
        form.add("AUTORISATION",autorisation);
        form.add("PAYS",pays);
        
        return form;
    }
    
    String formatReference(String reference){
        reference = reference.replaceAll("\\s","+");
        return reference;
    }
    
    boolean analyseReponse(String reponse){
        boolean valeur = true;
        String[] list = reponse.split("&");
        for(int i=0; i<list.length ; i++){
            if(list[i].contains("CODEREPONSE")){
                String[] listCode = list[i].split("=");
                System.out.println("Code reponse = "+listCode[1]);
                if(!listCode[1].equals("00000")){
                    valeur = false;
                }
                break;
            }
        }
        return valeur;
    }
}

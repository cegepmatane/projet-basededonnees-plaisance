import vue.Test;
import vue.VuePrincipale;

public class AilePlaisance 
{
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{    
		VuePrincipale vuePrincipale = new VuePrincipale(); //Enlever les commentaires pour avoir la vue sans la TableView
		vuePrincipale.launch(VuePrincipale.class, args);   //Enlever les commentaires pour avoir la vue sans la TableView
		//Test test = new Test();								 //Enlever les commentaires pour avoir la vue avec la TableView
		//Test.launch(Test.class, args);					     //Enlever les commentaires pour avoir la vue avec la TableView
	}
}

package controleur;

import vue.VuePrincipale;

public class Controleur 
{
	protected static Controleur instance;
	@SuppressWarnings("unused")
	private VuePrincipale vuePrincipale = null;
	
	public static Controleur getInstance()
	{
		if(instance == null) instance = new Controleur();
		return instance;
	}
	
	public void setVuePrincipale(VuePrincipale vuePrincipale)
	{
		this.vuePrincipale = vuePrincipale;
	}
	
	
}

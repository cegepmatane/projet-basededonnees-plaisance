package controleur;

import vue.VuePrincipale;

public class Controleur 
{
	protected static Controleur instance;
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

	public void actionModifierItem() 
	{
		this.vuePrincipale.construirePanneauModifierListe();
	}

	public void actionRetourEnArriere() 
	{
		this.vuePrincipale.construirePanneauListe();
	}

	public void actionAjouterItem() 
	{
		this.vuePrincipale.construirePanneauAjouterItem();
	}
	
	
}

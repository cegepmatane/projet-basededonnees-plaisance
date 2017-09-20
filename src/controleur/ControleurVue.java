package controleur;

import modele.ModeleBateau;
import vue.VuePrincipale;

public class ControleurVue 
{
	protected static ControleurVue instance;
	private VuePrincipale vuePrincipale = null;
	
	public static ControleurVue getInstance()
	{
		if(instance == null) instance = new ControleurVue();
		return instance;
	}
	
	public void setVuePrincipale(VuePrincipale vuePrincipale)
	{
		this.vuePrincipale = vuePrincipale;
	}

	public void actionModifierItem(ModeleBateau bateau) 
	{
		this.vuePrincipale.construirePanneauModifierListe(bateau);
	}

	public void actionRetourEnArriere() 
	{
		this.vuePrincipale.construirePanneauListe();
	}

	public void actionAjouterItem() 
	{
		this.vuePrincipale.construirePanneauAjouterItem();
	}

	public void actionSupprimerItem(ModeleBateau bateau)
	{
		this.vuePrincipale.construirePanneauSupprimerItem(bateau);
	}
	
	
}

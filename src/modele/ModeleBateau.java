package modele;

public class ModeleBateau
{
    private int id;
    private String nom;
    private String marque;
    private String modele;
    private int annee;
    private float longueur;
    private  float largeur;
    private  float hauteur;

    public ModeleBateau(int id ,String nom, String marque, String modele, int annee, float longueur, float largeur, float hauteur) {
        this.nom = nom;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }


    @Override
    public String toString() {
        return "ModeleBateau{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", annee=" + annee +
                ", longueur=" + longueur +
                ", largeur=" + largeur +
                ", hauteur=" + hauteur +
                '}';
    }
}

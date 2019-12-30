import java.awt.BorderLayout;
import java.awt.Component;

import fr.chupin.pl.dao.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import fr.chupin.pl.domaine.EnregistrementStrategie;
import fr.chupin.pl.domaine.HTMLStrategie;
import fr.chupin.pl.domaine.PDFStrategie;
import fr.chupin.pl.domaine.Playlist;
import fr.chupin.pl.domaine.Titre;
import fr.chupin.pl.domaine.XLSStrategie;
import fr.chupin.pl.service.CalculerDureePlaylist;
import fr.chupin.pl.service.CreerPlaylist;
import fr.chupin.pl.service.CreerTitre;
import fr.chupin.pl.service.RechercherPlaylistById;
import fr.chupin.pl.service.RechercherPlaylists;
import fr.chupin.pl.service.RechercherTitreById;
import fr.chupin.pl.service.RechercherTitres;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class jFrameMain extends JFrame {
	
	/**
	 * Create the frame.
	 */
	public jFrameMain() {
		
		initComponents();
		ChargerListPlaylists();
		ChargerListTitres();
		ComboInit();
			
	}
	
	
	/**
	 * Méthodes privées
	 */
	public void ChargerListPlaylists() {
		
		PlaylistDAO pDao = new PlaylistDAOMySQL(BDConnexion.getInstance());
		RechercherPlaylists rp = new RechercherPlaylists(pDao);
		List<Playlist> listPlaylist = rp.execute();
		
		//remplir la ListModel
		PlaylistListModel.clear();
		for (Playlist playlist : listPlaylist) {
			PlaylistListModel.addElement(playlist);
			}	
		this.L_Playlist_Playlists.setModel(PlaylistListModel);
	}
	
	public void ChargerListTitres() {
		
		TitreDAO pDao = new TitreDAOMySQL(BDConnexion.getInstance());
		RechercherTitres rt = new RechercherTitres(pDao);
		List<Titre> listTitre = rt.execute();
		
		//remplir la ListModel
		TitreListModel.clear();
		for (Titre titre : listTitre) {
			TitreListModel.addElement(titre);
		}
		this.L_Titre_Titres.setModel(TitreListModel);
	}
	
	
	private Playlist getPlaylistSelected() {
		return (Playlist) this.L_Playlist_Playlists.getSelectedValue();
	}
	
	private void RafraichirAffParamTitre(Titre pTitre) {
		
		if (pTitre != null) {
			TitreDAO pDAO = new TitreDAOMySQL(BDConnexion.getInstance());
		this.I_Titre_Param_Nom.setText(pTitre.getNom());
		this.I_Titre_Param_Groupe.setText(pTitre.getNomGroupe());
		this.I_Titre_Param_Duree.setText(String.valueOf(pTitre.getDuree()));
		}	
	}
	
	private void RafraichirAffParamPlaylist(Playlist pPlaylist) {
		
		if (pPlaylist != null) {
			PlaylistDAO pDao = new PlaylistDAOMySQL(BDConnexion.getInstance());
		this.I_Playlist_Param_Titre.setText(pPlaylist.getNom());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.I_Playlist_Param_DateCrea.setText(df.format(pPlaylist.getDateCrea()));	 
		CalculerDureePlaylist cdp = new CalculerDureePlaylist(pDao);
		this.I_Playlist_Duree_Totale.setText(String.valueOf(cdp.execute(pPlaylist)));
		}	
	}
	
	
	private boolean isStrategieEnregistrementSelected() {
		if (this.I_Playlist_Strategie_Export.getSelectedItem() == null || this.I_Playlist_Strategie_Export.getSelectedItem() == "") {
			return false;
		}
		else {
			return true;
		}
	}
	
	private Boolean fieldsAreNotEmptyCreerTitre() {
		if (this.I_Titre_Nouveau_Titre.getText().equals("") || this.I_Titre_Nouveau_Duree.getText().equals("") || this.I_Titre_Nouveau_Groupe.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Les champs requis doivent être saisis correctement !","Erreur de saisie",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else {
			
			return true;
		}
	}
	
	private Boolean fieldsAreNotEmptyCreerPlaylist() {

		if (I_Playlist_Titre.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Les champs requis doivent être saisis correctement !","Erreur de saisie",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else {
			
			return true;
		}
	}
	
	private void ComboInit() {
		
		CB_Playlist_Export.setBounds(149, 124, 160, 27);		
		CB_Playlist_Export.addItem(html);
		CB_Playlist_Export.addItem(pdf);
		CB_Playlist_Export.addItem(xls);
		P_Playlist_P_Param.add(CB_Playlist_Export);
		P_Playlist_1.add(P_Playlist_P_Param);
		contentPane.add(P_Playlist_1);
	}
	
	
	/* INITIALISATION DES COMPOSANTS
	 * 
	 */
	
	public void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,999,800);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * RECHERCHE
		 */
		
		I_Rechercher = new JTextField();
		I_Rechercher.setBounds(556, 17, 300, 26);	
		I_Rechercher.setColumns(10);
		contentPane.add(I_Rechercher);
		
		JButton BT_Rechercher = new JButton("Rechercher");
		BT_Rechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BT_Rechercher_actionPerformed(e);
			}
		});
		BT_Rechercher.setBounds(868, 17, 117, 29);
		contentPane.add(BT_Rechercher);
		
		
		/*
		 * PLAYLIST
		 */
	
		
		P_Playlist_1.setBounds(22, 58, 474, 525);
		P_Playlist_1.setToolTipText("");
		P_Playlist_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Playlists");
		lblNewLabel.setBounds(208, 2, 61, 16);
		P_Playlist_1.add(lblNewLabel);
		
		L_Playlist_Playlists = new JList();
		L_Playlist_Playlists.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						L_Playlist_PlaylistsSelectionValueChanged(e);
		    }
		});
		L_Playlist_Playlists.setBounds(13, 30, 457, 242);
		P_Playlist_1.add(L_Playlist_Playlists);
		
		JPanel P_Playlist_P_Nouvelle = new JPanel();
		P_Playlist_P_Nouvelle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		P_Playlist_P_Nouvelle.setBounds(13, 456, 455, 55);
		P_Playlist_1.add(P_Playlist_P_Nouvelle);
		P_Playlist_P_Nouvelle.setLayout(null);
		
		JLabel IL_Playlist_Titre = new JLabel("Titre");
		IL_Playlist_Titre.setBounds(32, 21, 61, 16);
		P_Playlist_P_Nouvelle.add(IL_Playlist_Titre);
		
		I_Playlist_Titre = new JTextField();
		I_Playlist_Titre.setColumns(10);
		I_Playlist_Titre.setBounds(151, 16, 165, 26);
		P_Playlist_P_Nouvelle.add(I_Playlist_Titre);
		
		JButton BT_Playlist_Creer_1 = new JButton("Créer");
		BT_Playlist_Creer_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BT_Playlist_CreerMouseCliked(e);
			}
		});
		BT_Playlist_Creer_1.setBounds(328, 16, 117, 29);
		P_Playlist_P_Nouvelle.add(BT_Playlist_Creer_1);
		
		P_Playlist_P_Param.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		P_Playlist_P_Param.setBounds(16, 284, 450, 158);
		P_Playlist_1.add(P_Playlist_P_Param);
		P_Playlist_P_Param.setLayout(null);
		
		I_Playlist_Param_Titre = new JTextField();
		I_Playlist_Param_Titre.setBounds(149, 6, 160, 26);
		P_Playlist_P_Param.add(I_Playlist_Param_Titre);
		I_Playlist_Param_Titre.setColumns(10);
		
		JLabel IL_Playlist_Param_Titre = new JLabel("Nom");
		IL_Playlist_Param_Titre.setBounds(29, 11, 61, 16);
		P_Playlist_P_Param.add(IL_Playlist_Param_Titre);
		
		JLabel IL_Playlist_Param_DateCrea = new JLabel("Date de création");
		IL_Playlist_Param_DateCrea.setBounds(29, 91, 103, 16);
		P_Playlist_P_Param.add(IL_Playlist_Param_DateCrea);
		
		JLabel IL_Playlist_Duree_Totale = new JLabel("Durée totale");
		IL_Playlist_Duree_Totale.setBounds(29, 53, 103, 16);
		P_Playlist_P_Param.add(IL_Playlist_Duree_Totale);
		
		I_Playlist_Param_DateCrea = new JTextField();
		I_Playlist_Param_DateCrea.setBounds(149, 86, 160, 26);
		P_Playlist_P_Param.add(I_Playlist_Param_DateCrea);
		I_Playlist_Param_DateCrea.setColumns(10);
		
		I_Playlist_Duree_Totale = new JTextField();
		I_Playlist_Duree_Totale.setBounds(149, 48, 160, 26);
		P_Playlist_P_Param.add(I_Playlist_Duree_Totale);
		I_Playlist_Duree_Totale.setColumns(10);
		
		JLabel label = new JLabel("Format export");
		label.setBounds(29, 128, 89, 16);
		P_Playlist_P_Param.add(label);
		
		JButton BT_Playlist_Export = new JButton("Exporter");
		BT_Playlist_Export.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BT_Playlist_Export(e); 
			}
		});
		BT_Playlist_Export.setBounds(327, 123, 117, 29);
		P_Playlist_P_Param.add(BT_Playlist_Export);
		contentPane.add(P_Playlist_1);
		
		/*
		 * TITRE
		 */
		
		JPanel P_Titre = new JPanel();
		P_Titre.setLayout(null);
		P_Titre.setToolTipText("");
		P_Titre.setBounds(496, 58, 474, 581);
		contentPane.add(P_Titre);
				
		JLabel lblTitres = new JLabel("Titres");
		lblTitres.setBounds(208, 2, 61, 16);
		P_Titre.add(lblTitres);
		
		L_Titre_Titres = new JList();
		L_Titre_Titres.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				L_Titre_TitresSelectionValueChanged(e);
		    }
		});
		L_Titre_Titres.setBounds(13, 30, 457, 242);
		P_Titre.add(L_Titre_Titres);
		
		JPanel P_Titre_Nouveau = new JPanel();
		P_Titre_Nouveau.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		P_Titre_Nouveau.setLayout(null);
		P_Titre_Nouveau.setBounds(13, 454, 453, 122);
		P_Titre.add(P_Titre_Nouveau);
		
		JLabel label_2 = new JLabel("Titre");
		label_2.setBounds(32, 21, 61, 16);
		P_Titre_Nouveau.add(label_2);
		
		JLabel lblDure = new JLabel(" Durée (secondes, entier)");
		lblDure.setBounds(28, 54, 156, 16);
		P_Titre_Nouveau.add(lblDure);
		
		I_Titre_Nouveau_Titre = new JTextField();
		I_Titre_Nouveau_Titre.setColumns(10);
		I_Titre_Nouveau_Titre.setBounds(151, 16, 165, 26);
		P_Titre_Nouveau.add(I_Titre_Nouveau_Titre);
		
		I_Titre_Nouveau_Duree = new JTextField();
		I_Titre_Nouveau_Duree.setColumns(10);
		I_Titre_Nouveau_Duree.setBounds(196, 49, 120, 26);
		P_Titre_Nouveau.add(I_Titre_Nouveau_Duree);
		
		JButton BT_Titre_Nouveau = new JButton("Créer");
		BT_Titre_Nouveau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BT_Titre_NouveauClicked(e);
			}
		});
		BT_Titre_Nouveau.setBounds(328, 16, 117, 29);
		P_Titre_Nouveau.add(BT_Titre_Nouveau);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setBounds(32, 87, 95, 16);
		P_Titre_Nouveau.add(lblGroupe);
		
		I_Titre_Nouveau_Groupe = new JTextField();
		I_Titre_Nouveau_Groupe.setColumns(10);
		I_Titre_Nouveau_Groupe.setBounds(151, 82, 165, 26);
		P_Titre_Nouveau.add(I_Titre_Nouveau_Groupe);
		
		JPanel P_Titre_Param = new JPanel();
		P_Titre_Param.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		P_Titre_Param.setLayout(null);
		P_Titre_Param.setBounds(16, 284, 450, 158);
		P_Titre.add(P_Titre_Param);
		
		I_Titre_Param_Nom = new JTextField();
		I_Titre_Param_Nom.setColumns(10);
		I_Titre_Param_Nom.setBounds(149, 6, 160, 26);
		P_Titre_Param.add(I_Titre_Param_Nom);
		
		JLabel label_4 = new JLabel("Nom");
		label_4.setBounds(29, 11, 61, 16);
		P_Titre_Param.add(label_4);
		
		JLabel lblGroupe_1 = new JLabel("Groupe");
		lblGroupe_1.setBounds(29, 91, 103, 16);
		P_Titre_Param.add(lblGroupe_1);
		
		JLabel lblDure_1 = new JLabel("Durée");
		lblDure_1.setBounds(29, 53, 103, 16);
		P_Titre_Param.add(lblDure_1);
		
		I_Titre_Param_Groupe = new JTextField();
		I_Titre_Param_Groupe.setColumns(10);
		I_Titre_Param_Groupe.setBounds(149, 86, 160, 26);
		P_Titre_Param.add(I_Titre_Param_Groupe);
		
		I_Titre_Param_Duree = new JTextField();
		I_Titre_Param_Duree.setColumns(10);
		I_Titre_Param_Duree.setBounds(149, 48, 160, 26);
		P_Titre_Param.add(I_Titre_Param_Duree);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrameMain frame = new jFrameMain();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}
		
		private void M_MenuTitreClicked(MouseEvent e) {
			this.P_Titres.setVisible(true);
			this.P_Playlist.setVisible(false);
			this.P_Param.setVisible(false);
		}
		
		private void M_MenuParamClicked(MouseEvent e) {
			this.P_Playlist.setVisible(false);
			this.P_Titres.setVisible(false);
			this.P_Param.setVisible(true);
		} 
		
		/**
		 * Afficher les paramètres du titre sélectionné
		 * @param e
		 */
		private void L_Titre_TitresSelectionValueChanged(ListSelectionEvent e) {
			Titre vTitre = (Titre) L_Titre_Titres.getSelectedValue();
			RafraichirAffParamTitre(vTitre);
		}
		
		/**
		 * Afficher les paramètres de la playlist sélectionnée dans la liste
		 * @param e
		 */
		private void L_Playlist_PlaylistsSelectionValueChanged(ListSelectionEvent e) {
			
			Playlist vPlaylist = (Playlist) L_Playlist_Playlists.getSelectedValue();
			RafraichirAffParamPlaylist(vPlaylist);
		 }
		
		/**
		 * Rechercher une playlist et un titre par ID et l'afficher dans la liste
		 * @param e
		 */
		private void BT_Rechercher_actionPerformed(ActionEvent e) {
			
			if (I_Rechercher.getText().isEmpty()) {
				this.ChargerListPlaylists();
				this.ChargerListTitres();
			}
			else {
				// Titres
				TitreDAO tDao = new TitreDAOMySQL(BDConnexion.getInstance());
				RechercherTitreById rtId = new RechercherTitreById(tDao);
				if (rtId.execute(I_Rechercher.getText()) != null) {
					this.TitreListModel.clear();
					this.TitreListModel.addElement(rtId.execute(I_Rechercher.getText()));
					this.L_Titre_Titres.setModel(TitreListModel);
				}
				
				// Playlist
				PlaylistDAO pDao = new PlaylistDAOMySQL(BDConnexion.getInstance());
				RechercherPlaylistById rpId = new RechercherPlaylistById(pDao);
				if (rpId.execute(I_Rechercher.getText()) != null) {
					this.PlaylistListModel.clear();
					this.PlaylistListModel.addElement(rpId.execute(I_Rechercher.getText()));
					this.L_Playlist_Playlists.setModel(PlaylistListModel);
				}
			}	
		}
			
		/**
		 * Créer une playlist
		 * @param e
		 */
		private void BT_Playlist_CreerMouseCliked(MouseEvent e) {
			
			if(fieldsAreNotEmptyCreerPlaylist() == true) {
				PlaylistDAO pDao = new PlaylistDAOMySQL(BDConnexion.getInstance());
				CreerPlaylist cp = new CreerPlaylist(pDao);
				java.sql.Date date;
				Calendar today = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				java.sql.Date JavaDateObject = new java.sql.Date(calendar.getTime().getTime());
				Playlist p = new Playlist(this.I_Playlist_Titre.getText(), JavaDateObject);
				if (cp.execute(p)) {
					I_Playlist_Titre.setText("");
					ChargerListPlaylists();
				}
				
			}
		}
		
		/**
		 * Créer un titre
		 * @param e
		 */
		private void BT_Titre_NouveauClicked(MouseEvent e) {
			
			if(fieldsAreNotEmptyCreerTitre() == true) {
				TitreDAO pDao = new TitreDAOMySQL(BDConnexion.getInstance());
				CreerTitre ct = new CreerTitre(pDao);
				Titre t = new Titre(this.I_Titre_Nouveau_Titre.getText(), Integer.parseInt(this.I_Titre_Nouveau_Duree.getText()), this.I_Titre_Nouveau_Groupe.getText());
				if (ct.execute(t)) {
					I_Titre_Nouveau_Titre.setText("");
					I_Titre_Nouveau_Duree.setText("");
					I_Titre_Nouveau_Groupe.setText("");
					ChargerListTitres();
				}
				
			}
		}
		
		/**
		 * Exporter une playlist au format choisi
		 * @param e
		 */
		private void BT_Playlist_Export(MouseEvent e) {
			Playlist vOnePlaylistSelected = (Playlist) L_Playlist_Playlists.getSelectedValue();
			 if (vOnePlaylistSelected != null) {
				 
				if (CB_Playlist_Export.getSelectedItem() == html) {
					vOnePlaylistSelected.setStrategie(html);
					vOnePlaylistSelected.EnregistrerSous();
				}
				
				if (CB_Playlist_Export.getSelectedItem() == pdf) {
					vOnePlaylistSelected.setStrategie(pdf);
					vOnePlaylistSelected.EnregistrerSous();
				}
					
				if (CB_Playlist_Export.getSelectedItem() == xls) {
					vOnePlaylistSelected.setStrategie(xls);
					vOnePlaylistSelected.EnregistrerSous();
				}
			 }
		}
		
	private List<Playlist> playlists;
	private List<Titre> titres;
	private JTextField I_Rechercher;
	private JTextField I_Playlist_Param_Titre;
	private JTextField I_Playlist_Titre;
	private JComboBox<EnregistrementStrategie> CB_Playlist_Export = new JComboBox<EnregistrementStrategie>();
	private DefaultListModel<Playlist> PlaylistListModel = new DefaultListModel<Playlist>();
	private DefaultListModel<Titre> TitreListModel = new DefaultListModel<Titre>();
	private JList<Playlist> L_Playlist_Playlists = new JList<Playlist>();
	private JList<Titre> L_Titre_Titres = new JList<Titre>();
	private JTabbedPane TP_Player;
	private JButton BT_Titre_Nouveau;
	private JButton BT_Playlist_Creer;
	private JPanel P_Playlist = new JPanel();
	private JPanel P_Titres = new JPanel();
	private JPanel P_Param = new JPanel();
	private JComboBox I_Playlist_Strategie_Export;
	private JTextField I_Playlist_Param_DateCrea;
	private JTextField I_Playlist_Duree_Totale;
	private JTextField I_Titre_Nouveau_Titre;
	private JTextField I_Titre_Nouveau_Duree;
	private JTextField I_Titre_Param_Nom;
	private JTextField I_Titre_Param_Groupe;
	private JTextField I_Titre_Param_Duree;
	private JTextField I_Titre_Nouveau_Groupe;
	private HTMLStrategie html = new HTMLStrategie("html");
	private PDFStrategie pdf = new PDFStrategie("pdf");
	private XLSStrategie xls = new XLSStrategie("xls");
	private JPanel P_Playlist_P_Param = new JPanel();
	private JPanel P_Playlist_1 = new JPanel();
	private JPanel contentPane = new JPanel();
}

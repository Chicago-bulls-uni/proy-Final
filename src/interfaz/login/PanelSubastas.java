package interfaz.login;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import models.Propietario;
import models.Subasta;

public class PanelSubastas extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Subastita> listota;
    private DefaultListModel<Subastita> listita;
    public PanelSubastas(Propietario propietary) {
        setLayout(new BorderLayout());
        setVisible(true);
        JPanel title = new JPanel(new GridLayout(1, 7));
        title.add(new JLabel("idSubasta"));
        title.add(new JLabel("piezas"));
        title.add(new JLabel(" Precio Minimo"));
        title.add(new JLabel("Oferta Mas alta"));
        title.add(new JLabel("Fecha final"));
        title.add(new JLabel("activo"));
        add(title, BorderLayout.NORTH);

        listita = new DefaultListModel<>();
        recorrerMapaSubastas(Propietario.getGaleria().getMapaSubastas());

        listota = new JList<>(listita);
        listota.setCellRenderer(new SubastaListCellRenderer());

        JScrollPane listaSub = new JScrollPane(listota);
        add(listaSub, BorderLayout.CENTER);
    }

    public void recorrerMapaSubastas(Map<Integer, Subasta> Subastas) {
        for (Subasta subi : Subastas.values()) {
            Subastita sub = new Subastita(subi);
            listita.addElement(sub);
        }
    }

    public class SubastaListCellRenderer extends JPanel implements ListCellRenderer<Subastita> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel nameLabel;
        private JLabel priceLabel;
        private JLabel ofertamaxlabel;
        private SimpleDateFormat dateFormat;
        private JLabel dateLabel;
        private JLabel detailsLabel;
        private JLabel autorLabel;

        public SubastaListCellRenderer() {
            setLayout(new GridLayout(1, 6));
            nameLabel = new JLabel();
            priceLabel = new JLabel();
            ofertamaxlabel = new JLabel();
            dateLabel = new JLabel();
            autorLabel = new JLabel();
            detailsLabel = new JLabel();

            add(nameLabel);
            add(priceLabel);
            add(ofertamaxlabel);  
            add(dateLabel);
            add(autorLabel);
            add(detailsLabel);

            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Subastita> list, Subastita subasta, int index, boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText("Nombre de las Pieza: " + subasta.getPiezas());
            priceLabel.setText("Precio Actual: " + subasta.getValorMinimo());
            ofertamaxlabel.setText("Oferta mÃ¡s alta: " + subasta.getOfertaMax());
            dateLabel.setText("Fecha de Fin: " + dateFormat.format(subasta.getFechafin()));
            autorLabel.setText("Propietario: " + subasta.getPropietariopieza());
            detailsLabel.setText("Activa: " + subasta.isActivo());

            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

            return this;
        }
    }
}
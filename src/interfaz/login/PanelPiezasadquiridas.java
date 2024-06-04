package interfaz.login;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import models.Pieza;
import models.Propietario;

public class PanelPiezasadquiridas extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<PiezaRender> listota;
    private DefaultListModel<PiezaRender> listita;
    private Propietario propetary;

    public PanelPiezasadquiridas(Propietario Propetary) {
        propetary = Propetary;

        setLayout(new BorderLayout());
        setVisible(true);

        JPanel title = new JPanel(new GridLayout(1, 12));
        title.add(new JLabel("idPieza"));
        title.add(new JLabel("Nombre"));
        title.add(new JLabel("tipo"));
        title.add(new JLabel("bloqueada"));
        title.add(new JLabel("autor"));
        title.add(new JLabel("lugarCreacion"));
        title.add(new JLabel("dimensiones"));
        title.add(new JLabel("fechaIngresa"));
        title.add(new JLabel("fechaVenta"));
        title.add(new JLabel("precio"));
        title.add(new JLabel("propietario"));
        title.add(new JLabel("DetallesEspecificos"));
        add(title, BorderLayout.NORTH);

        if (!propetary.getPiezasadquiridad().isEmpty()) {
            listita = new DefaultListModel<>();
            recorrerMapaListaPiezas(propetary.getPiezasadquiridad());
            listota = new JList<>(listita);
            listota.setCellRenderer(new PieceRenderListCellRenderer());

            JScrollPane listaSub = new JScrollPane(listota);
            add(listaSub, BorderLayout.CENTER);
        }
    }

    public void recorrerMapaListaPiezas(List<Pieza> Piezasadquiridas) {
        for (Pieza subi : Piezasadquiridas) {
            PiezaRender sub = new PiezaRender(subi);
            listita.addElement(sub);
        }
    }

    public class PieceRenderListCellRenderer extends JPanel implements ListCellRenderer<PiezaRender> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel idLabel;
        private JLabel nameLabel;
        private JLabel typeLabel;
        private JLabel lockedLabel;
        private JLabel authorLabel;
        private JLabel creationPlaceLabel;
        private JLabel dimensionsLabel;
        private JLabel entryDateLabel;
        private JLabel saleDateLabel;
        private JLabel priceLabel;
        private JLabel ownerLabel;
        private JLabel specificDetailsLabel;
        public PieceRenderListCellRenderer() {
            setLayout(new GridLayout(1, 12));
            idLabel = new JLabel();
            nameLabel = new JLabel();
            typeLabel = new JLabel();
            lockedLabel = new JLabel();
            authorLabel = new JLabel();
            creationPlaceLabel = new JLabel();
            dimensionsLabel = new JLabel();
            entryDateLabel = new JLabel();
            saleDateLabel = new JLabel();
            priceLabel = new JLabel();
            ownerLabel = new JLabel();
            specificDetailsLabel = new JLabel();

            add(idLabel);
            add(nameLabel);
            add(typeLabel);
            add(lockedLabel);
            add(authorLabel);
            add(creationPlaceLabel);
            add(dimensionsLabel);
            add(entryDateLabel);
            add(saleDateLabel);
            add(priceLabel);
            add(ownerLabel);
            add(specificDetailsLabel);

            new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends PiezaRender> list, PiezaRender pieza, int index, boolean isSelected, boolean cellHasFocus) {
            idLabel.setText("ID Pieza: " + pieza.getIdPieza());
            nameLabel.setText("Nombre: " + pieza.getNombre());
            typeLabel.setText("Tipo: " + pieza.getTipo());
            lockedLabel.setText("Bloqueada: " + (pieza.isBloqueada() ? "Sí" : "No"));
            authorLabel.setText("Autor: " + pieza.getAutor());
            creationPlaceLabel.setText("Lugar de Creación: " + pieza.getLugarCreacion());
            dimensionsLabel.setText("Dimensiones: " + pieza.getDimensiones());
            entryDateLabel.setText("Fecha de Ingreso: " + pieza.getFechaIngresa());
            saleDateLabel.setText("Fecha de Venta: " + pieza.getFechaVenta());
            priceLabel.setText("Precio: " + pieza.getPrecio());
            ownerLabel.setText("Propietario: " + pieza.getPropietario());
            specificDetailsLabel.setText("Detalles Específicos: " + pieza.getDetallesEspecificos());

            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

            return this;
        }
    }
}
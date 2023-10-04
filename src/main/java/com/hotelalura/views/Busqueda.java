package com.hotelalura.views;

import com.hotelalura.controller.BusquedaController;
import com.hotelalura.modelos.Huesped;
import com.hotelalura.modelos.Reserva;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	private BusquedaController busquedaController;
	int xMouse, yMouse;


	/**
	 * Create the frame.
	 */
	public Busqueda() {
		busquedaController = new BusquedaController();


		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/img/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);




		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/img/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.getColumnModel().getColumn(0).setCellEditor(null);
		cargarTablaReservas();
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/img/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		tbHuespedes.getColumnModel().getColumn(0).setCellEditor(null);
		tbHuespedes.getColumnModel().getColumn(6).setCellEditor(null);
		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/img/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(txtBuscar.getText().isBlank()){
					JOptionPane.showMessageDialog(null, "Por favor, ingresa algún dato a buscar.");
					return;
				}
				limpiarTabla(modelo);
				limpiarTabla(modeloH);
				if(Objects.equals(txtBuscar.getText(), "all") || Objects.equals(txtBuscar.getText(), "todo")){
					cargarTablaHuespedes();
					cargarTablaReservas();
					return;
				}
				cargarTablasBusqueda();
				repintarForzadamente();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tieneFilaElegida(tbHuespedes) && tieneFilaElegida(tbReservas)){
					JOptionPane.showMessageDialog(null, "Por favor, elige solo un único item de una sola tabla.");
					return;
				}
				if(tieneFilaElegida(tbReservas)){
					if(tbReservas.isEditing()){
						JOptionPane.showMessageDialog(null, "Sal de la celda que ya editaste para guardar cambios y luego presiona Editar.");
						tbHuespedes.clearSelection();
						tbReservas.clearSelection();
						return;
					}
					modificarReserva();
					limpiarTabla(modelo);
					cargarTablaReservas();
					return;
				}
				if(tieneFilaElegida(tbHuespedes)){
					if(tbHuespedes.isEditing()){
						JOptionPane.showMessageDialog(null, "Sal de la celda que ya editaste para guardar cambios y luego presiona Editar.");
						return;
					}
					modificarHuesped();
					limpiarTabla(modeloH);
					cargarTablaHuespedes();
					return;
				}
				JOptionPane.showMessageDialog(null, "Por favor, elige un item");
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tieneFilaElegida(tbHuespedes) && tieneFilaElegida(tbReservas)){
					JOptionPane.showMessageDialog(null, "Por favor, elige solo un unico item de una sola tabla");
					tbHuespedes.clearSelection();
					tbReservas.clearSelection();
					return;
				}
				if(tieneFilaElegida(tbReservas)){
					eliminarPorReserva();
					limpiarTabla(modelo);
					cargarTablaReservas();
					return;
				}
				if(tieneFilaElegida(tbHuespedes)){
					eliminarPorHuesped();
					limpiarTabla(modeloH);
					cargarTablaHuespedes();
					return;
				}
				JOptionPane.showMessageDialog(null, "Por favor, elige un item");
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private boolean tieneFilaElegida(JTable modelo) {
		return !(modelo.getSelectedRowCount() == 0 || modelo.getSelectedColumnCount() == 0);
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	public Integer tomarNumeros(String palabraConNumeros) {
		StringBuilder numbersOnly = new StringBuilder();
		for (char c : palabraConNumeros.toCharArray()) {
			if (Character.isDigit(c)) {
				numbersOnly.append(c);
			}
		}
		return Integer.parseInt(String.valueOf(numbersOnly));
	}

	// manejo con reservas

	private void limpiarTabla(DefaultTableModel modelo) {
		modelo.getDataVector().clear();
	}
	private void cargarTablaReservas() {
		var reservas = busquedaController.listarReservas();
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(),
				reserva.getFechaE(),
				reserva.getFechaS(),
				reserva.getValor()+"$",
				reserva.getFormaPago() }));
	}

	private void eliminarPorReserva() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer idReserva = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					busquedaController.eliminarPorReserva(idReserva);
					modelo.removeRow(tbReservas.getSelectedRow());
					JOptionPane.showMessageDialog(this, "Reserva y huésped eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	private void modificarReserva() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer idReserva = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					String fechaE = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
					String fechas = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
					Integer valor = tomarNumeros(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
					String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);

					busquedaController.modificarReserva(new Reserva(idReserva,fechaE,fechas, valor,formaPago));

					JOptionPane.showMessageDialog(this, "Item modificado con éxito!");

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
// Huesped
private void cargarTablaHuespedes() {
	var huespeds = busquedaController.listarHuespedes();
	huespeds.forEach(huesped -> modeloH.addRow(new Object[] { huesped.getId(),
			huesped.getNombre(),
			huesped.getApellido(),
			huesped.getFechaNacimiento(),
			huesped.getNacionalidad(),
			huesped.getTelefono(),
			huesped.getIdReserva()
	}));

}
	private void eliminarPorHuesped() {
		Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer idReserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
					busquedaController.eliminarPorReserva(idReserva);
					modeloH.removeRow(tbHuespedes.getSelectedRow());
					JOptionPane.showMessageDialog(this, "Reserva y huésped eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	private void modificarHuesped() {
		Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer idHuesped = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					String nombre= (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
					String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2);
					String fechaNacimiento = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3);
					String nacionalidad = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4);
					String telefono = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5);
					Integer idReservaF = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

					busquedaController.modificarHuesped(new Huesped(idHuesped,nombre,apellido, fechaNacimiento,nacionalidad,telefono,idReservaF));

					JOptionPane.showMessageDialog(this, "Item modificado con éxito!");

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	// Busqueda
	private void cargarTablasBusqueda() {
		var datos = busquedaController.buscarPorPalabraClave(txtBuscar.getText());
		var huespeds = (List<Huesped>) datos.get(0);
		var reservas =(List<Reserva>) datos.get(1);
		huespeds.forEach(huesped -> modeloH.addRow(new Object[] { huesped.getId(),
				huesped.getNombre(),
				huesped.getApellido(),
				huesped.getFechaNacimiento(),
				huesped.getNacionalidad(),
				huesped.getTelefono(),
				huesped.getIdReserva()
		}));
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(),
				reserva.getFechaE(),
				reserva.getFechaS(),
				reserva.getValor()+" $",
				reserva.getFormaPago() }));
	}
	public void repintarForzadamente(){
		tbReservas.revalidate();
		tbReservas.repaint();
		tbHuespedes.revalidate();
		tbHuespedes.repaint();
	}
}

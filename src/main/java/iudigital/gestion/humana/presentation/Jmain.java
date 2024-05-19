package iudigital.gestion.humana.presentation;

import iudigital.gestion.humana.Controllers.FuncionarioController;
import iudigital.gestion.humana.Controllers.GeneroController;
import iudigital.gestion.humana.Controllers.estadoCivilController;
import iudigital.gestion.humana.Controllers.tipoDocController;
import iudigital.gestion.humana.domain.EstadoCivil;
import iudigital.gestion.humana.domain.Funcionario;
import iudigital.gestion.humana.domain.Genero;
import iudigital.gestion.humana.domain.TiposDoc;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jaime
 */
public class Jmain extends javax.swing.JFrame {

    private final FuncionarioController funcionarioController;
    private final GeneroController generoController;
    private final estadoCivilController estadocivilController;
    private final tipoDocController tiposdocController;
    private static final String[] COLUMNS ={"ID","TIPO DOCUMENTO",
        "DOCUMENTO", "NOMBRES","APELLIDOS","ESTADO CIVIL",
        "GENERO","DIRECCION","TELEFONO","FECHA DE NACIMIENTO"};
    private static final String GENEROS = "---GENEROS";
    private static final String SELECCIONE = "----SELECCIONE----";
    private static final String TIPO_DOCUMENTO = "---TIPO DOCUMENTO";
    private static final String ESTADO_CIVIL = "---ESTADO CIVIL";
 
    
    
    
    public Jmain() {
        initComponents();
        funcionarioController = new FuncionarioController();
        generoController = new GeneroController();
        estadocivilController = new estadoCivilController();
        tiposdocController = new tipoDocController();
        listFuncionarios();
        listGeneros();
        listTipoDoc();
        listeCivil();
        addListener();
        cbxTipoDoc.setEditable(false);
        cbxGenero.setEditable(false);
    }
    private void listFuncionarios(){
        
       cbxFuncionarios.removeAllItems();
       Funcionario funcionarioUno = new Funcionario();
       funcionarioUno.setNombres(SELECCIONE);
       cbxFuncionarios.addItem(funcionarioUno);
       tblFuncionarios.removeAll();
       
        DefaultTableModel defalDefaultTableModel = new DefaultTableModel();
        for (String COLUMN : COLUMNS) {
            defalDefaultTableModel.addColumn(COLUMN);
        }
        
        tblFuncionarios.setModel(defalDefaultTableModel);
        
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            if (funcionarios.isEmpty()) {
                return;
            }
            defalDefaultTableModel.setRowCount(funcionarios.size());
            int row = 0;
            for (Funcionario funcionario: funcionarios) {
                defalDefaultTableModel.setValueAt(funcionario.getId(), row, 0);
                defalDefaultTableModel.setValueAt(funcionario.getTipoDocumento(), row, 1);
                defalDefaultTableModel.setValueAt(funcionario.getNroDocumento(), row, 2);
                defalDefaultTableModel.setValueAt(funcionario.getNombres(), row, 3);
                defalDefaultTableModel.setValueAt(funcionario.getApellidos(), row, 4);
                defalDefaultTableModel.setValueAt(funcionario.getEstadoCivil(), row, 5);
                defalDefaultTableModel.setValueAt(funcionario.getGenero(), row, 6);
                defalDefaultTableModel.setValueAt(funcionario.getDireccion(), row, 7);
                defalDefaultTableModel.setValueAt(funcionario.getTelefono(), row, 8);
                defalDefaultTableModel.setValueAt(funcionario.getFechaNacimiento(), row, 9);
                row++;
                
                cbxFuncionarios.addItem(funcionario);
            }
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }  
        
    }
    private void listGeneros() {
        cbxGenero.removeAllItems();
        Genero genreUno = new Genero();
        genreUno.setName(GENEROS);
        cbxGenero.addItem(genreUno);
        try {
            List<Genero> generos = generoController.getGenres();
            for (Genero genero : generos) {
                cbxGenero.addItem(genero);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void listTipoDoc() {
        cbxTipoDoc.removeAllItems();
        TiposDoc tiposdocUno = new TiposDoc();
        tiposdocUno.setName(TIPO_DOCUMENTO);
        cbxTipoDoc.addItem(tiposdocUno);
        try {
            List<TiposDoc> tipDoc = tiposdocController.getDoc();
            for (TiposDoc tpD : tipDoc) {
                cbxTipoDoc.addItem(tpD);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void listeCivil() {
        cbxEstadoCivil.removeAllItems();
        EstadoCivil estadosCivilUno = new EstadoCivil();
        estadosCivilUno.setName(ESTADO_CIVIL);
        cbxEstadoCivil.addItem(estadosCivilUno);
        try {
            List<EstadoCivil> eciviles = estadocivilController.getEstados();
            for (EstadoCivil ecv : eciviles) {
                cbxEstadoCivil.addItem(ecv);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addListener() {
        cbxFuncionarios.addItemListener(event -> {
            Funcionario selectedFuncionario = (Funcionario) event.getItem();
            if (selectedFuncionario.getNombres().equals(SELECCIONE)) {
                txtIdEdit.setText("");
                txtIdTipoDocEdit.setText("");
                txtTipodDocEdit.setText("");
                txtDocument.setText("");
                txtNombreEdit.setText("");
                txtApellidosEdit.setText("");
                txtIdEstadoCivilEDIT.setText("");
                txtEstadoCivilEdit.setText("");
                txtIDGENEROEdit.setText("");
                txtGeneneroEdit.setText("");
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");
            } else {
                txtIdEdit.setText(String.valueOf(selectedFuncionario.getId()));
                txtIdTipoDocEdit.setText(String.valueOf(selectedFuncionario.getTid()));
                txtTipodDocEdit.setText(selectedFuncionario.getTipoDocumento());
                txtDocument.setText(selectedFuncionario.getNroDocumento());
                txtNombreEdit.setText(selectedFuncionario.getNombres());
                txtApellidosEdit.setText(selectedFuncionario.getApellidos());
                txtIdEstadoCivilEDIT.setText(String.valueOf(selectedFuncionario.getEid()));
                txtEstadoCivilEdit.setText(String.valueOf(selectedFuncionario.getEstadoCivil()));
                txtIDGENEROEdit.setText(String.valueOf(selectedFuncionario.getGid()));
                txtGeneneroEdit.setText(String.valueOf(selectedFuncionario.getGenero()));
                txtTelefonoEdit.setText(String.valueOf(selectedFuncionario.getTelefono()));
                txtDireccionEdit.setText(String.valueOf(selectedFuncionario.getDireccion()));
                txtFechaNacimientoEdit.setText(String.valueOf(selectedFuncionario.getFechaNacimiento()));
                
            }
        });
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JWindows = new javax.swing.JTabbedPane();
        JpDelete = new javax.swing.JPanel();
        JlTitle1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLtipodoc = new javax.swing.JLabel();
        jLDocumento = new javax.swing.JLabel();
        jLNombres = new javax.swing.JLabel();
        jLApellidos = new javax.swing.JLabel();
        jLestadoCivil = new javax.swing.JLabel();
        jlGenero = new javax.swing.JLabel();
        jlTelefono = new javax.swing.JLabel();
        jLNacimiento = new javax.swing.JLabel();
        cbxTipoDoc = new javax.swing.JComboBox<>();
        txtDocumneto = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        cbxEstadoCivil = new javax.swing.JComboBox<>();
        cbxGenero = new javax.swing.JComboBox<>();
        txtTelefono = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();
        jlDir = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        JpSave = new javax.swing.JPanel();
        JlTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        jlIdEdit = new javax.swing.JLabel();
        txtIdEdit = new javax.swing.JTextField();
        jlIdTipoDocEdit = new javax.swing.JLabel();
        txtIdTipoDocEdit = new javax.swing.JTextField();
        jlDocumentEdit = new javax.swing.JLabel();
        txtDocument = new javax.swing.JTextField();
        jlNombreEdit = new javax.swing.JLabel();
        txtNombreEdit = new javax.swing.JTextField();
        jlApellidosEdit = new javax.swing.JLabel();
        txtApellidosEdit = new javax.swing.JTextField();
        jlTipoDocEdit = new javax.swing.JLabel();
        txtTipodDocEdit = new javax.swing.JTextField();
        jlIdestadoCivilEdit = new javax.swing.JLabel();
        txtIdEstadoCivilEDIT = new javax.swing.JTextField();
        jlEstadoCivilEdit = new javax.swing.JLabel();
        txtEstadoCivilEdit = new javax.swing.JTextField();
        jlIdGneroEdit = new javax.swing.JLabel();
        jlGeneroEdit = new javax.swing.JLabel();
        txtGeneneroEdit = new javax.swing.JTextField();
        jlDireccionEdit = new javax.swing.JLabel();
        txtDireccionEdit = new javax.swing.JTextField();
        jlTelefonoEdit = new javax.swing.JLabel();
        txtTelefonoEdit = new javax.swing.JTextField();
        btnActulizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jlFechaNacimientoEdit = new javax.swing.JLabel();
        txtFechaNacimientoEdit = new javax.swing.JTextField();
        txtIDGENEROEdit = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JlTitle1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        JlTitle1.setText("CONTROL FUNCIONARIOS IUD ANTIOQUIA");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Diligenciar los siguientes campos"));

        jLtipodoc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLtipodoc.setText("TIPO DOCUMENTO");

        jLDocumento.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLDocumento.setText("DOCUMENTO");

        jLNombres.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLNombres.setText("NOMBRES");

        jLApellidos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLApellidos.setText("APELLIDOS");

        jLestadoCivil.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLestadoCivil.setText("ESTADO CIVIL");

        jlGenero.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlGenero.setText("GENERO");

        jlTelefono.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlTelefono.setText("TELEFONO");

        jLNacimiento.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLNacimiento.setText("FECHA NACIMIENTO");

        jbSave.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jbSave.setText("GUARDAR");
        jbSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveActionPerformed(evt);
            }
        });

        jlDir.setText("DIRECCION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLtipodoc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDocumneto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDocumento)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLNombres)
                            .addComponent(jbSave)
                            .addComponent(jlTelefono))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLApellidos))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLestadoCivil))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLNacimiento)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jlDir)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLtipodoc)
                    .addComponent(jLDocumento)
                    .addComponent(jLNombres)
                    .addComponent(jLApellidos)
                    .addComponent(jLestadoCivil)
                    .addComponent(jlGenero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumneto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNacimiento)
                            .addComponent(jlTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jlDir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jbSave))
        );

        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblFuncionarios);

        javax.swing.GroupLayout JpDeleteLayout = new javax.swing.GroupLayout(JpDelete);
        JpDelete.setLayout(JpDeleteLayout);
        JpDeleteLayout.setHorizontalGroup(
            JpDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpDeleteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JlTitle1)
                .addGap(157, 157, 157))
            .addGroup(JpDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpDeleteLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        JpDeleteLayout.setVerticalGroup(
            JpDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpDeleteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(JlTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
        );

        JWindows.addTab("GUARDAR / EDITAR", JpDelete);

        JlTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        JlTitle.setText("CONTROL FUNCIONARIOS IUD ANTIOQUIA");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actulizar Funcionarios"));

        jLabel1.setText("FUNCIONARIOS");

        jlIdEdit.setText("ID");

        txtIdEdit.setEditable(false);

        jlIdTipoDocEdit.setText("ID TIPO DOC");

        txtIdTipoDocEdit.setEditable(false);

        jlDocumentEdit.setText("DOCUMENTO");

        jlNombreEdit.setText("NOMBRES");

        jlApellidosEdit.setText("APELLIDOS");

        jlTipoDocEdit.setText("TIPO DOC");

        txtTipodDocEdit.setEditable(false);

        jlIdestadoCivilEdit.setText("ID ESTADO CIVIL");

        txtIdEstadoCivilEDIT.setEditable(false);

        jlEstadoCivilEdit.setText("ESTADO CIVIL");

        txtEstadoCivilEdit.setEditable(false);

        jlIdGneroEdit.setText("ID GENERO");

        jlGeneroEdit.setText("GENERO");

        txtGeneneroEdit.setEditable(false);

        jlDireccionEdit.setText("DIRECCION");

        jlTelefonoEdit.setText("TELEFONO");

        btnActulizar.setText("ACTUALIZAR");
        btnActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jlFechaNacimientoEdit.setText("FECHA DE NACIMIENTO");

        txtFechaNacimientoEdit.setEditable(false);

        txtIDGENEROEdit.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jlIdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlIdTipoDocEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdTipoDocEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlTipoDocEdit)
                        .addGap(1, 1, 1)
                        .addComponent(txtTipodDocEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlIdestadoCivilEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdEstadoCivilEDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jlEstadoCivilEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlGeneroEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGeneneroEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addComponent(jlDireccionEdit)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlFechaNacimientoEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jlTelefonoEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jlIdGneroEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIDGENEROEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jlDocumentEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jlNombreEdit)
                            .addGap(18, 18, 18)
                            .addComponent(txtNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jlApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIdEdit)
                    .addComponent(txtIdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlIdTipoDocEdit)
                    .addComponent(txtIdTipoDocEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTipoDocEdit)
                    .addComponent(txtTipodDocEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNombreEdit)
                    .addComponent(txtNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDocumentEdit)
                    .addComponent(txtDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIdestadoCivilEdit)
                    .addComponent(txtIdEstadoCivilEDIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlEstadoCivilEdit)
                    .addComponent(txtEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlIdGneroEdit)
                    .addComponent(txtIDGENEROEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlGeneroEdit)
                    .addComponent(txtGeneneroEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDireccionEdit)
                    .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTelefonoEdit)
                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFechaNacimientoEdit)
                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout JpSaveLayout = new javax.swing.GroupLayout(JpSave);
        JpSave.setLayout(JpSaveLayout);
        JpSaveLayout.setHorizontalGroup(
            JpSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpSaveLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(JlTitle)
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(JpSaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        JpSaveLayout.setVerticalGroup(
            JpSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpSaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JlTitle)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JWindows.addTab("EDITAR/ELIMINAR", JpSave);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JWindows)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JWindows)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        if (txtDocumneto.getText().trim().length() == 0) { //trim es para que no deje espacios
            JOptionPane.showMessageDialog(null, "Digite el Documento"); // mostramos el mensaje si el campo está vacío
            txtDocumneto.requestFocus();
            return;
        }

        if (txtName.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Degite el nombre");
            txtName.requestFocus();
            return;
        }

        if (txtApellidos.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite el apellido");
            txtApellidos.requestFocus();
            return;
        }
        if (txtDir.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite la Direccion");
            txtDir.requestFocus();
            return;
        }
        if (txtTelefono.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite el telefono");
            txtTelefono.requestFocus();
            return;
        }
        if (txtFechaNacimiento.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento");
            txtFechaNacimiento.requestFocus();
            return;
        }
        

        Funcionario funcionario = new Funcionario();
        funcionario.setTipoDocumento(String.valueOf(cbxTipoDoc.getSelectedIndex() + 1));
        funcionario.setNroDocumento(txtDocumneto.getText().trim());
        funcionario.setNombres(txtName.getText().trim());
        funcionario.setApellidos(txtApellidos.getText().trim());
        funcionario.setEstadoCivil(String.valueOf(cbxEstadoCivil.getSelectedIndex() + 1));
        funcionario.setGenero(String.valueOf(cbxGenero.getSelectedIndex() + 1));
        funcionario.setDireccion(txtDir.getText().trim());
        funcionario.setTelefono(txtTelefono.getText().trim());
        funcionario.setTelefono(txtFechaNacimiento.getText().trim());
      
        try {
            funcionarioController.create(funcionario);
            txtDocumneto.setText("");
            txtName.setText("");
            txtApellidos.setText("");
            txtDir.setText("");
            txtTelefono.setText("");
            txtFechaNacimiento.setText("");
            listFuncionarios();
            JOptionPane.showMessageDialog(null, "Funcionario creado con éxito");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "El Funcionario  no pudo ser creado");
        }
    }//GEN-LAST:event_jbSaveActionPerformed

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
     if (txtIdEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un funcionario de la lista");
            txtIdEdit.requestFocus();
            return;
        }

        if (txtTipodDocEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el tipo de documento");
            txtTipodDocEdit.requestFocus();
            return;
        }

        if (txtDocument.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el documento");
            txtDocument.requestFocus();
            return;
        }

        if (txtNombreEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el nombre");
            txtNombreEdit.requestFocus();
            return;
        }

        if (txtApellidosEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el apellido");
            txtApellidosEdit.requestFocus();
            return;
        }

        if (txtEstadoCivilEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el estado civil");
            txtEstadoCivilEdit.requestFocus();
            return;
        }
        if (txtGeneneroEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el Genero");
            txtGeneneroEdit.requestFocus();
            return;
        }
        if (txtDireccionEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite la direccion");
            txtDireccionEdit.requestFocus();
            return;
        }
        if (txtTelefonoEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite el telefono");
            txtTelefonoEdit.requestFocus();
            return;
        }
        if (txtFechaNacimientoEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor digite la fecha de nacimiento");
            txtFechaNacimientoEdit.requestFocus();
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setTipoDocumento(txtTipodDocEdit.getText().trim());
        funcionario.setNroDocumento(txtDocumneto.getText().trim());
        funcionario.setNombres(txtName.getText().trim());
        funcionario.setApellidos(txtApellidos.getText().trim());
        funcionario.setApellidos(txtEstadoCivilEdit.getText().trim());
        funcionario.setApellidos(txtGeneneroEdit.getText().trim());
        funcionario.setDireccion(txtDir.getText().trim());
        funcionario.setTelefono(txtTelefono.getText().trim());
        funcionario.setFechaNacimiento(txtFechaNacimiento.getText().trim());

        int opt = JOptionPane.showConfirmDialog(null, "Desea actulizar el funcionario", "Confrimar Salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opt == 0) {

            try {
                funcionarioController.updateFuncionario(Integer.parseInt(txtIdEdit.getText()), funcionario);
                txtIdEdit.setText("");
                txtIdTipoDocEdit.setText("");
                txtTipodDocEdit.setText("");
                txtDocument.setText("");
                txtNombreEdit.setText("");
                txtApellidosEdit.setText("");
                txtIdEstadoCivilEDIT.setText("");
                txtEstadoCivilEdit.setText("");
                txtIDGENEROEdit.setText("");
                txtGeneneroEdit.setText("");
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText(" ");
                listFuncionarios();
                JOptionPane.showMessageDialog(null, "Funcionario actualizado con exito");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo actaulizar el funcionario"+ ERROR);
            }
        }
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
            if (txtIdEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un funcionario de la lista");
            txtIdEdit.requestFocus();
            return;
        }
        int opt = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el funcionario"
                + "", "Confrimar Salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opt == 0) {

            try {
                funcionarioController.deleteFuncionario(Integer.parseInt(txtIdEdit.getText()));
                txtIdEdit.setText("");
                txtIdTipoDocEdit.setText("");
                txtTipodDocEdit.setText("");
                txtDocument.setText("");
                txtNombreEdit.setText("");
                txtApellidosEdit.setText("");
                txtIdEstadoCivilEDIT.setText("");
                txtEstadoCivilEdit.setText("");
                txtIDGENEROEdit.setText("");
                txtGeneneroEdit.setText("");
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText(" ");
                listFuncionarios();
                JOptionPane.showMessageDialog(null, "Funcionario eliminado con exito");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el funcionario");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jmain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane JWindows;
    private javax.swing.JLabel JlTitle;
    private javax.swing.JLabel JlTitle1;
    private javax.swing.JPanel JpDelete;
    private javax.swing.JPanel JpSave;
    private javax.swing.JButton btnActulizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<EstadoCivil> cbxEstadoCivil;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarios;
    private javax.swing.JComboBox<Genero> cbxGenero;
    private javax.swing.JComboBox<TiposDoc> cbxTipoDoc;
    private javax.swing.JLabel jLApellidos;
    private javax.swing.JLabel jLDocumento;
    private javax.swing.JLabel jLNacimiento;
    private javax.swing.JLabel jLNombres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLestadoCivil;
    private javax.swing.JLabel jLtipodoc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlApellidosEdit;
    private javax.swing.JLabel jlDir;
    private javax.swing.JLabel jlDireccionEdit;
    private javax.swing.JLabel jlDocumentEdit;
    private javax.swing.JLabel jlEstadoCivilEdit;
    private javax.swing.JLabel jlFechaNacimientoEdit;
    private javax.swing.JLabel jlGenero;
    private javax.swing.JLabel jlGeneroEdit;
    private javax.swing.JLabel jlIdEdit;
    private javax.swing.JLabel jlIdGneroEdit;
    private javax.swing.JLabel jlIdTipoDocEdit;
    private javax.swing.JLabel jlIdestadoCivilEdit;
    private javax.swing.JLabel jlNombreEdit;
    private javax.swing.JLabel jlTelefono;
    private javax.swing.JLabel jlTelefonoEdit;
    private javax.swing.JLabel jlTipoDocEdit;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosEdit;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtDireccionEdit;
    private javax.swing.JTextField txtDocument;
    private javax.swing.JTextField txtDocumneto;
    private javax.swing.JTextField txtEstadoCivilEdit;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaNacimientoEdit;
    private javax.swing.JTextField txtGeneneroEdit;
    private javax.swing.JTextField txtIDGENEROEdit;
    private javax.swing.JTextField txtIdEdit;
    private javax.swing.JTextField txtIdEstadoCivilEDIT;
    private javax.swing.JTextField txtIdTipoDocEdit;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNombreEdit;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEdit;
    private javax.swing.JTextField txtTipodDocEdit;
    // End of variables declaration//GEN-END:variables
}

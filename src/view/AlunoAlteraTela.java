/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.AlunoDAO;
import DAO.EnderecoDAO;
import DAO.EstadoDAO;
import DAO.InformacaoDAO;
import DAO.ResponsavelDAO;
import DAO.TurmaDAO;
import DAO.TurnoDAO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Acesso;
import model.Aluno;
import model.Endereco;
import model.Estado;
import model.Informacao;
import model.Responsavel;
import model.Telefone;
import model.Turma;
import model.Turno;
import relatorio.Relatorio;

import util.BDException;
import util.CustomSplashScreen;
import util.FormataData;

/**
 *
 * @author dev
 */
public final class AlunoAlteraTela extends javax.swing.JFrame{
    private Aluno aluno = new Aluno();
    private Aluno alunoFoto;
    private AlunoDAO alunoDAO;
    private List<Aluno> alunos;
    private Acesso acesso; 
    private List<Turma> turmas;
    private TurmaDAO turmaDAO;
    private Endereco endereco = new Endereco();
    private EnderecoDAO enderecoDAO;
    private List<Responsavel> responsaveis;
    private Responsavel responsavel =new Responsavel();
    private ResponsavelDAO responsavelDAO;
    private Telefone telefone = new Telefone();
    private Informacao informacao = new Informacao();
    private List<Estado> estados;
    private EstadoDAO estadoDAO;
    private List<Turno> turnos;
    private TurnoDAO turnoDAO;
    private FormataData formataData;
 
    
    
    //private final List<Servidor> lista = new ArrayList<>();
   
    private File imagem;
    
    private List texto = new ArrayList();
       
    public AlunoAlteraTela() {
        initComponents();
        this.setLocationRelativeTo(null);
     
    }
    
     public AlunoAlteraTela(Aluno aluno, Acesso acesso) throws ParseException, BDException {
        this();
        this.acesso = acesso;
        this.aluno = aluno;
        labelvisibelfalse();
        alunoPreenchido();
        responsavelPreenchido();
        preencherComboTurma();
        preencherComboEstado();
        preencherComboTurno();
        labelinf.setVisible(false);
        labelEndereco.setVisible(false);
        jTextArea1.setLineWrap(true);      
         
    }
    public void preencherComboTurma(){      
        turmas = new ArrayList<>();
        turmaDAO = new TurmaDAO();
        try {          
           turmas =  turmaDAO.listar(acesso);
           jComboBoxTurma.removeAllItems();
        for (Turma turma : turmas) {
              //  comboMateria.addItem(materia.getId()+" - "+ materia.getNome());
              jComboBoxTurma.addItem( turma.getTurma());
             
              if(turma.getTurma().equals(aluno.getTurma().getTurma())){
                 jComboBoxTurma.setSelectedItem(turma.getTurma());
             }              
            }
        } catch (Exception e) {
        }  
    }
    public void preencherComboEstado(){
        estados = new ArrayList<>();
        estadoDAO = new EstadoDAO();
        try {           
           estados =  estadoDAO.listar(acesso);
            jComboBoxEstado.removeAllItems();
        for (Estado estado : estados) {
               jComboBoxEstado.addItem( estado.getEstado()); 
               if(estado.getEstado().equals(aluno.getEstado().getEstado())){
                   jComboBoxEstado.setSelectedItem(estado.getEstado());
               }
        }
                  
        } catch (Exception e) {
        }
        //jComboBoxEstado.removeAllItems();
     
     }
    public void preencherComboTurno(){
        turnos = new ArrayList<>();
        turnoDAO = new TurnoDAO();
        
        try {
            turnos =  turnoDAO.listar(acesso);
            jComboBoxTurno.removeAllItems();
              for (Turno turno : turnos) {          
              jComboBoxTurno.addItem( turno.getTurno());
              if(turno.getTurno().equals(aluno.getTurno().getTurno())){
                  jComboBoxTurno.setSelectedItem(turno.getTurno());
              }
              }
        } catch (BDException ex) {
            Logger.getLogger(AlunoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
         

     }
    
    public void responsavelPreenchido() throws BDException{
        
        responsaveis = new ArrayList<>();
        responsavelDAO = new ResponsavelDAO();
        responsaveis = responsavelDAO.pesquisarMae(aluno, acesso);
        for (Responsavel responsavelm : responsaveis) {
            labelEndereco.setText(String.valueOf(responsavelm.getEndereco().getId()));
            campoNomeMae.setText(responsavelm.getNome());
            campoLocalTrabalhoMae.setText(responsavelm.getLocalTrabalho());
            campoProfissaoMae.setText(responsavelm.getProfissao());
            campoTelefoneTrabalhoMae.setText(responsavelm.getFoneTrabalho());
            campoTelefoneMae.setText(responsavelm.getTelefone().getNumero());
           
            campoCep.setText(responsavelm.getEndereco().getCep());
            campoRua.setText(responsavelm.getEndereco().getLogradouro());
            campoNumero.setText(responsavelm.getEndereco().getNumero());
            campoBairro.setText(responsavelm.getEndereco().getBairro());     
            
        }
        responsaveis = responsavelDAO.pesquisarPai(aluno, acesso);
        for (Responsavel responsavelp : responsaveis) {
            labelEndereco.setText(String.valueOf(responsavelp.getEndereco().getId()));
            campoNomePai.setText(responsavelp.getNome());
            campoLocalTrabalhoPai.setText(responsavelp.getLocalTrabalho());
            campoProfissaoPai.setText(responsavelp.getProfissao());
            campoTelefoneTrabalhoPai.setText(responsavelp.getFoneTrabalho());
            campoTelefonePai.setText(responsavelp.getTelefone().getNumero());
            
            campoCep.setText(responsavelp.getEndereco().getCep());
            campoRua.setText(responsavelp.getEndereco().getLogradouro());
            campoNumero.setText(responsavelp.getEndereco().getNumero());
            campoBairro.setText(responsavelp.getEndereco().getBairro());
            
        }
    }
 
    public void alunoPreenchido() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        alunos = new ArrayList<>();
        alunoDAO = new AlunoDAO();
        try {
            alunos = alunoDAO.pesquisarAux(aluno, acesso);
            for (Aluno aluno1 : alunos) {
                aluno = aluno1;
                campoMatricula.setText(aluno1.getMatricula());
                campoNomeAluno.setText(aluno1.getNome());
                campoNaturalidade.setText(aluno1.getNaturalidade());
                Date dataFormatada = formato.parse(aluno1.getDatanascimento());
                jDateChooser1.setDate(dataFormatada);
                if(aluno1.getSexo().equals("F")){
                    jRadioButtonF.setSelected(true);
                }else{
                    jRadioButtonM.setSelected(true);
                }
                abrirImagem(aluno1.getFoto());
                //alunoFoto.setFoto(aluno1.getFoto());
                labelinf.setText(String.valueOf(aluno1.getInformacao().getCodigo()));

                    
                if(aluno1.getInformacao().getPergunta1().equals("S")){
                    radiosimp1.setSelected(true);
                    labelp1.setVisible(false);
                    campop1.setVisible(false);
                }else{
                    radionaop1.setSelected(true);
                    campop1.setVisible(true);
                    campop1.setText(aluno1.getInformacao().getPergunta1());
                }
                if(aluno1.getInformacao().getPergunta2().equals("N")){
                    radionaop2.setSelected(true);
                    labelp2.setVisible(false);
                    campop2.setVisible(false);
                }else{
                    radiosimp2.setSelected(true);
                     labelp2.setVisible(true);
                     campop2.setVisible(true);
                    campop2.setText(aluno1.getInformacao().getPergunta2());
                }
                if(aluno1.getInformacao().getPergunta3().equals("N")){
                    radionaop3.setSelected(true);
                    labelp3.setVisible(false);
                    campop3.setVisible(false);
                }else{
                    radiosimp3.setSelected(true);
                    labelp3.setVisible(true);
                    campop3.setVisible(true);
                    campop3.setText(aluno1.getInformacao().getPergunta3());
                }
                //gera saida do valor de cada elemento do array
                String s = aluno1.getInformacao().getPergunta4();
                    if( s.contains("Catapora"))
                        jCheckBoxcatapora.setSelected(true);
                    if(s.contains("Sarampo"))
                        jCheckBoxsarampo.setSelected(true);
                    if(s.contains("Caxumba"))
                        jCheckBoxCaxumba.setSelected(true);
                    if(s.contains("Convulsão"))
                        jCheckBoxConvulsao.setSelected(true);
                    if(s.contains("Desmaio"))
                        jCheckBoxDesmaio.setSelected(true);
                     
//                }
            
                
                
                if(aluno1.getInformacao().getPergunta5().equals("N")){
                    radionaop5.setSelected(true);
                
                }else{
                    radiosimp5.setSelected(true);
                
                }
                if(aluno1.getInformacao().getPergunta6().equals("N")){
                    radionaop6.setSelected(true);
                    
                }else{
                    radiosimp6.setSelected(true);
              
                }
                if(aluno1.getInformacao().getPergunta7().equals("N")){
                    radionaop7.setSelected(true);
                    labelp7.setVisible(false);
                    campop7.setVisible(false);
                }else{
                    radiosimp7.setSelected(true);
                    labelp7.setVisible(true);
                    campop7.setVisible(true);
                    campop7.setText(aluno.getInformacao().getPergunta7());
                }
                if(aluno1.getInformacao().getPergunta8().equals("N")){
                    radionaop8.setSelected(true);
                    labelp8.setVisible(false);
                    campop8.setVisible(false);
                }else{
                    radiosimp8.setSelected(true);
                    labelp8.setVisible(true);
                    campop8.setVisible(true);
                    campop8.setText(aluno.getInformacao().getPergunta8());
                }
                if(aluno1.getInformacao().getPergunta9().equals("N")){
                    radionaop9.setSelected(true);
                    labelp9.setVisible(false);
                    campop9.setVisible(false);
                }else{
                    radiosimp9.setSelected(true);
                    labelp9.setVisible(true);
                    campop9.setVisible(true);
                    campop9.setText(aluno.getInformacao().getPergunta9());
                }
                 jTextArea1.setText(aluno.getInformacao().getObs());
            }
        } catch (BDException ex) {
            Logger.getLogger(AlunoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirImagem(Object source){
       if(source instanceof File){
           ImageIcon imageIcon = new ImageIcon(imagem.getAbsolutePath()); 
                Image image = imageIcon.getImage();
             Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        /* Fim do código do redimensionamento */
        
        ImageIcon icon = new ImageIcon(newimg);
        labelFoto.setIcon(icon); 
    
       }else if(source instanceof byte[]){
         //   * OPCIONAL - Código    para definir o tamanho da imagem na tela */
        ImageIcon imageIcon = new ImageIcon(aluno.getFoto()); 
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        /* Fim do código do redimensionamento */
        
        ImageIcon icon = new ImageIcon(newimg);
        labelFoto.setIcon(icon);  
    }
  
    }
    public void labelvisibelfalse(){
                labelp1.setVisible(false);
                labelp2.setVisible(false);
                labelp3.setVisible(false);
                labelp7.setVisible(false);
                labelp8.setVisible(false);
                labelp9.setVisible(false); 
                campop1.setVisible(false);
                campop2.setVisible(false);
                campop3.setVisible(false);
                campop7.setVisible(false);
                campop8.setVisible(false);
                campop9.setVisible(false);
    }

    public void buscarCep(String cep){
        String json;        

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
            
            // JOptionPane.showMessageDialog(null, json);
            
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");                       
            String array[] = new String[30];
            array = json.split("\n");
            
            // JOptionPane.showMessageDialog(null, array);
           
            endereco.setLogradouro(array[7]);
            endereco.setBairro(array[15]);
//            end.setCidade(array[19]);
//            end.setUf(array[23]);
      
            
         //   campoRua.setText(end.getLogradouro());
         //   campoBairro.setText(end.getBairro());
         //   jTxtCidade.setText(cidade);
         //   jTxtEstado.setText(uf);
            //JOptionPane.showMessageDialog(null, logradouro + " " + bairro + " " + cidade + " " + uf);
            
        } catch (Exception e) {
           // throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "CEP não encontrado! Digite o Endereço");
        }
    }
    public void preencheCampos(){
        campoRua.setText(endereco.getLogradouro());
        campoBairro.setText(endereco.getBairro());      
    }
    public void preencheEndereco(){
        endereco.setId(Long.parseLong(labelEndereco.getText()));
        endereco.setCep(campoCep.getText());
        endereco.setLogradouro(campoRua.getText());
        endereco.setNumero(campoNumero.getText());
        endereco.setBairro(campoBairro.getText());
    } 
    public void preencheResponsavel(){
        responsavel.getEndereco().setId(Long.parseLong(labelEndereco.getText()));
        responsavel.setNome(campoNomeMae.getText());
        responsavel.setLocalTrabalho(campoLocalTrabalhoMae.getText());
        responsavel.setProfissao(campoProfissaoMae.getText());      
        responsavel.setFoneTrabalho(campoTelefoneTrabalhoMae.getText());
        responsavel.getTelefone().setNumero(campoTelefoneMae.getText());
        responsavel.setParentesco("Mãe");
       
    }
    public void preencheResponsavel2(){     
        responsavel.setNome(campoNomePai.getText());
        responsavel.setLocalTrabalho(campoLocalTrabalhoPai.getText());
        responsavel.setProfissao(campoProfissaoPai.getText());
        responsavel.setFoneTrabalho(campoTelefoneTrabalhoPai.getText());
        responsavel.getTelefone().setNumero(campoTelefonePai.getText());        
        responsavel.setParentesco("Pai");
        responsavel.getEndereco().setId(Long.parseLong(labelEndereco.getText()));
    }
    
    public void preencherInformacao(){
        informacao.setCodigo(Long.parseLong(labelinf.getText()));
        if(radiosimp1.isSelected()){
            informacao.setPergunta1("S");
        }else{
            informacao.setPergunta1(campop1.getText());
        }
        if(radionaop2.isSelected()){
            informacao.setPergunta2("N");
        }else{        
            informacao.setPergunta2(campop2.getText());
        }
         if(radionaop3.isSelected()){
            informacao.setPergunta3("N");
        }else{
            informacao.setPergunta3(campop3.getText());
        } 
        informacao.setPergunta4(texto.toString().substring(1,texto.toString().length()-1));
        if(radionaop5.isSelected()){
            informacao.setPergunta5("N");
        }else{
            informacao.setPergunta5("S");
        }
        if(radionaop6.isSelected()){
            informacao.setPergunta6("N");
        }else{
            informacao.setPergunta6("S");
        }
         if(radionaop7.isSelected()){
            informacao.setPergunta7("N");
        }else{
            informacao.setPergunta7(campop7.getText());
        }
        if(radionaop8.isSelected()){
            informacao.setPergunta8("N");
        }else{ 
            informacao.setPergunta8(campop8.getText());
        }    
        if(radionaop9.isSelected()){
            informacao.setPergunta9("N");
        }else{
            informacao.setPergunta9(campop9.getText());
        }
        informacao.setObs(jTextArea1.getText());
    }    

    public void preehcherAluno(){
        aluno.setMatricula(campoMatricula.getText());
        Date data = jDateChooser1.getDate();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
        String novaData = formatador.format(data);
         System.out.println("view.AlunoCadastroTela.preehcherAluno()"+novaData);
        aluno.setNome(campoNomeAluno.getText());
        aluno.setDatanascimento(novaData);
       
        if(jRadioButtonF.isSelected()){
            aluno.setSexo("F");
        }else{
            aluno.setSexo("M");
        }
        aluno.setFoto(getFoto());
        aluno.setNaturalidade(campoNaturalidade.getText());
       // aluno.setEstado((String) jComboBoxEstado.getSelectedItem());
       // aluno.setTurno((String) jComboBoxTurno.getSelectedItem());
       
       
       //setar codigo da estado
        Estado est = estados.get(jComboBoxEstado.getSelectedIndex());
           long x = est.getId();
           System.out.println("matx"+x);
           //conteudo.setMateria(x);
          // conteudo.getMateria().setId(x); 
           aluno.getEstado().setId(x);
   
        //setar codigo da turma
        Turma tur = turmas.get(jComboBoxTurma.getSelectedIndex());
           long y = tur.getId();
           System.out.println("maty"+y);
           //conteudo.setMateria(x);
          // conteudo.getMateria().setId(x); 
           aluno.getTurma().setId(y);
        
         //setar codigo da turno
        Turno tno = turnos.get(jComboBoxTurma.getSelectedIndex());
           long z = tno.getId();
           System.out.println("matz"+z);
           //conteudo.setMateria(x);
          // conteudo.getMateria().setId(x); 
           aluno.getTurno().setId(z);
        
       
        aluno.setStatus("Ativo");
        
        
        
    }

     private void alterarBD() throws Exception{
       EnderecoDAO dao= new EnderecoDAO();
        dao.alterar(endereco, acesso); 
       ResponsavelDAO daoR = new ResponsavelDAO();
        daoR.alterar(responsavel, acesso);
        if(!campoNomePai.getText().isEmpty()){
            responsavel = new Responsavel();
            preencheResponsavel2();
            daoR.alterar(responsavel, acesso);
        }
        
       InformacaoDAO daoI = new InformacaoDAO();
        daoI.alterar(informacao, acesso);
       AlunoDAO daoA = new AlunoDAO();
       daoA.alterar(aluno, acesso);
        JOptionPane.showMessageDialog(null, aluno.getNome() + "Alterado com sucesso!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
       
    }

    public void InserirFoto(){
        JFileChooser chooser = new JFileChooser();
        //aceitar so imagens do tipo jpg e png
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagem em JPEG e PNG", "jpg","png");
        chooser.addChoosableFileFilter(filtro);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        //setar o diretorio padaro
        chooser.setCurrentDirectory(new File(System.getProperty("user.home"))); 
        chooser.showOpenDialog(this);
        
        imagem = chooser.getSelectedFile();  
            //chooser.showOpenDialog(null);
        //String foto = f.getAbsolutePath();
        //jtextfield.setText(foto);
        
        /* OPCIONAL - Código para definir o tamanho da imagem na tela */
        ImageIcon imageIcon = new ImageIcon(imagem.getPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        /* Fim do código do redimensionamento */
        
        ImageIcon icon = new ImageIcon(newimg);
        labelFoto.setIcon(icon);        
        }
    
    private byte[] getFoto(){
        boolean isPng = false;
        if(imagem !=null){
            isPng = imagem.getName().endsWith("png");
            
            try {
                BufferedImage image = ImageIO.read(imagem);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int type = BufferedImage.TYPE_INT_RGB;
                
                if(isPng){
                    type = BufferedImage.BITMASK;
                }
                if(isPng){
                    ImageIO.write(image, "png", out);
                }else{
                    ImageIO.write(image, "jpg", out);
                }
                out.flush();
                byte[] byteArray = out.toByteArray();
                out.close();
                return byteArray;
                
            } catch (Exception e) {
            }
            
         
        }
        return null;
        
    }
    
//    public void relatorio(){
//        Relatorio relatorio = new Relatorio();
//       
//        relatorio.gerarRelatorio(campoMatricula, acesso);
//    
//    
//    }
    
    public void Alterar() throws Exception{
        preencherInformacao();
        preencheEndereco();
        preencheResponsavel();
        preehcherAluno();
        alterarBD();
       
        
    
    }
      public void relatorio(){
//           CustomSplashScreen.splashInit();
//
//    for (int i = 0; i < 10; i++) {
//        CustomSplashScreen.splashProgress(i * 20);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//    }
        Relatorio relatorio = new Relatorio();       
        relatorio.gerarRelatorio(campoMatricula, acesso);
    
    }
    public void deletar(){
         alunoDAO = new AlunoDAO();
            
        try{
            alunoDAO.deletar(aluno, acesso);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados"+e);
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSexo = new javax.swing.ButtonGroup();
        buttonGroupMoraCrianca = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campoNomeAluno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        labelFoto = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButtonF = new javax.swing.JRadioButton();
        jRadioButtonM = new javax.swing.JRadioButton();
        campoNaturalidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxTurno = new javax.swing.JComboBox<>();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jComboBoxTurma = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        campoCep = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        campoRua = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        campoBairro = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        campoNumero = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        campoNomeMae = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        campoProfissaoMae = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        campoLocalTrabalhoMae = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        campoTelefoneMae = new javax.swing.JFormattedTextField();
        campoTelefoneTrabalhoMae = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        campoNomePai = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        campoTelefonePai = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        campoLocalTrabalhoPai = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        campoProfissaoPai = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        campoTelefoneTrabalhoPai = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        radiosimp1 = new javax.swing.JRadioButton();
        radionaop1 = new javax.swing.JRadioButton();
        labelp1 = new javax.swing.JLabel();
        campop1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        radiosimp2 = new javax.swing.JRadioButton();
        radionaop2 = new javax.swing.JRadioButton();
        labelp2 = new javax.swing.JLabel();
        campop2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        radiosimp3 = new javax.swing.JRadioButton();
        radionaop3 = new javax.swing.JRadioButton();
        labelp3 = new javax.swing.JLabel();
        campop3 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jCheckBoxcatapora = new javax.swing.JCheckBox();
        jCheckBoxsarampo = new javax.swing.JCheckBox();
        jCheckBoxCaxumba = new javax.swing.JCheckBox();
        jCheckBoxConvulsao = new javax.swing.JCheckBox();
        jCheckBoxDesmaio = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        radiosimp5 = new javax.swing.JRadioButton();
        radionaop5 = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        radiosimp6 = new javax.swing.JRadioButton();
        radionaop6 = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        radiosimp7 = new javax.swing.JRadioButton();
        radionaop7 = new javax.swing.JRadioButton();
        labelp7 = new javax.swing.JLabel();
        campop7 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        radiosimp8 = new javax.swing.JRadioButton();
        radionaop8 = new javax.swing.JRadioButton();
        labelp8 = new javax.swing.JLabel();
        campop8 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        radiosimp9 = new javax.swing.JRadioButton();
        radionaop9 = new javax.swing.JRadioButton();
        labelp9 = new javax.swing.JLabel();
        campop9 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        botaoAlterar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoVoltar = new javax.swing.JButton();
        labelinf = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JTextField();
        botaoVoltaN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Alteração Aluno");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Aluno(A)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(38, 38, 177))); // NOI18N

        jLabel5.setForeground(new java.awt.Color(38, 38, 177));
        jLabel5.setText("Aluno (a):");

        jLabel6.setForeground(new java.awt.Color(38, 38, 177));
        jLabel6.setText("Data Nasc.:");

        jDateChooser1.setForeground(new java.awt.Color(38, 38, 177));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(38, 38, 177));
        jLabel7.setText("Natural de:");

        labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/foto.png"))); // NOI18N
        labelFoto.setText("Foto");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(38, 38, 177));
        jLabel9.setText("Sexo:");

        jRadioButtonF.setBackground(new java.awt.Color(38, 38, 177));
        buttonGroupSexo.add(jRadioButtonF);
        jRadioButtonF.setForeground(new java.awt.Color(38, 38, 177));
        jRadioButtonF.setSelected(true);
        jRadioButtonF.setText("Feminino");

        buttonGroupSexo.add(jRadioButtonM);
        jRadioButtonM.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jRadioButtonM.setForeground(new java.awt.Color(38, 38, 177));
        jRadioButtonM.setText("Masculino");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(38, 38, 177));
        jLabel10.setText("Estado:");

        jLabel11.setForeground(new java.awt.Color(38, 38, 177));
        jLabel11.setText("Turma:");

        jLabel12.setForeground(new java.awt.Color(38, 38, 177));
        jLabel12.setText("Turno:");

        jComboBoxTurno.setBackground(new java.awt.Color(38, 38, 177));
        jComboBoxTurno.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboBoxTurno.setForeground(new java.awt.Color(38, 38, 177));
        jComboBoxTurno.setToolTipText("");

        jComboBoxEstado.setBackground(new java.awt.Color(38, 38, 177));
        jComboBoxEstado.setToolTipText("");

        jButton2.setBackground(new java.awt.Color(38, 38, 177));
        jButton2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton2.setForeground(java.awt.Color.white);
        jButton2.setText("Incluir Foto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxTurma.setBackground(new java.awt.Color(38, 38, 177));
        jComboBoxTurma.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(campoNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(campoNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTurma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonF)
                            .addComponent(jRadioButtonM))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(campoNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(campoNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jRadioButtonF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jComboBoxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonM)
                            .addComponent(jLabel11)
                            .addComponent(jComboBoxTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addGap(47, 47, 47))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(38, 38, 177));
        jLabel13.setText("CEP:");

        try {
            campoCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setForeground(new java.awt.Color(38, 38, 177));
        jLabel14.setText("Logradouro:");

        jLabel15.setForeground(new java.awt.Color(38, 38, 177));
        jLabel15.setText("Bairro:");

        botaoPesquisar.setBackground(new java.awt.Color(38, 38, 177));
        botaoPesquisar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoPesquisar.setForeground(java.awt.Color.white);
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(38, 38, 177));
        jLabel16.setText("Número:");

        labelEndereco.setText("end");
        labelEndereco.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCep, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEndereco)))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBairro)
                        .addGap(22, 22, 22))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(campoCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar)
                    .addComponent(jLabel16)
                    .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEndereco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados dos Responsáveis"));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(38, 38, 177));
        jLabel17.setText("Nome da Mãe:");

        campoNomeMae.setToolTipText("sss");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(38, 38, 177));
        jLabel18.setText("Profissão:");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(38, 38, 177));
        jLabel19.setText("Local de Trabalho:");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(38, 38, 177));
        jLabel20.setText("Telefone:");

        try {
            campoTelefoneMae.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefoneMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneMaeActionPerformed(evt);
            }
        });

        try {
            campoTelefoneTrabalhoMae.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel21.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(38, 38, 177));
        jLabel21.setText("Tel. Trabalho");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(38, 38, 177));
        jLabel22.setText("Nome da Pai:");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(38, 38, 177));
        jLabel23.setText("Telefone:");

        try {
            campoTelefonePai.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefonePai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefonePaiActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(38, 38, 177));
        jLabel24.setText("Local de Trabalho:");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(38, 38, 177));
        jLabel25.setText("Profissão:");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(38, 38, 177));
        jLabel26.setText("Tel. Trabalho");

        try {
            campoTelefoneTrabalhoPai.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(1, 1, 1)
                                .addComponent(campoLocalTrabalhoPai, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)
                                .addGap(1, 1, 1)
                                .addComponent(campoProfissaoPai, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(37, 37, 37)
                                .addComponent(campoNomePai)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoTelefoneTrabalhoPai)
                            .addComponent(campoTelefonePai, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campoLocalTrabalhoMae, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addGap(1, 1, 1)
                                .addComponent(campoProfissaoMae, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoNomeMae))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoTelefoneTrabalhoMae, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(campoTelefoneMae))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(campoNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(campoTelefoneMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(campoLocalTrabalhoMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(campoProfissaoMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTelefoneTrabalhoMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(campoNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(campoTelefonePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(campoLocalTrabalhoPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(campoProfissaoPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTelefoneTrabalhoPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais"));

        jLabel27.setText("1- Os pais moram juntos?");

        buttonGroupMoraCrianca.add(radiosimp1);
        radiosimp1.setText("Sim");
        radiosimp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp1ActionPerformed(evt);
            }
        });

        buttonGroupMoraCrianca.add(radionaop1);
        radionaop1.setText("Não");
        radionaop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop1ActionPerformed(evt);
            }
        });

        labelp1.setText("Com quem mora a criança? ");

        campop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campop1ActionPerformed(evt);
            }
        });

        jLabel28.setText("2- A criança já estudou em outra escola?");

        buttonGroup1.add(radiosimp2);
        radiosimp2.setText("Sim");
        radiosimp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radionaop2);
        radionaop2.setText("Não");
        radionaop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop2ActionPerformed(evt);
            }
        });

        labelp2.setText("Qual o nome da escola");

        jLabel29.setText("3- A criança possui algum tipo de alergia?");

        buttonGroup2.add(radiosimp3);
        radiosimp3.setText("Sim");
        radiosimp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp3ActionPerformed(evt);
            }
        });

        buttonGroup2.add(radionaop3);
        radionaop3.setText("Não");
        radionaop3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop3ActionPerformed(evt);
            }
        });

        labelp3.setText("Qual alergia?");

        campop3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campop3ActionPerformed(evt);
            }
        });

        jLabel30.setText("4- Já teve? ");

        jCheckBoxcatapora.setText("Catapora");
        jCheckBoxcatapora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxcataporaActionPerformed(evt);
            }
        });

        jCheckBoxsarampo.setText("Sarampo");
        jCheckBoxsarampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxsarampoActionPerformed(evt);
            }
        });

        jCheckBoxCaxumba.setText("Caxumba");
        jCheckBoxCaxumba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCaxumbaActionPerformed(evt);
            }
        });

        jCheckBoxConvulsao.setText("Convulsão");
        jCheckBoxConvulsao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxConvulsaoActionPerformed(evt);
            }
        });

        jCheckBoxDesmaio.setText("Desmaio");
        jCheckBoxDesmaio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDesmaioActionPerformed(evt);
            }
        });

        jLabel31.setText("5- Costuma ter febre sem motivo aparente?");

        buttonGroup3.add(radiosimp5);
        radiosimp5.setText("Sim");
        radiosimp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp5ActionPerformed(evt);
            }
        });

        buttonGroup3.add(radionaop5);
        radionaop5.setText("Não");
        radionaop5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop5ActionPerformed(evt);
            }
        });

        jLabel32.setText("6- Chora sem motivo aparente?");

        buttonGroup4.add(radiosimp6);
        radiosimp6.setText("Sim");
        radiosimp6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp6ActionPerformed(evt);
            }
        });

        buttonGroup4.add(radionaop6);
        radionaop6.setText("Não");
        radionaop6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop6ActionPerformed(evt);
            }
        });

        jLabel33.setText("7- Faz acompanhamento médico com algum especialista?");

        buttonGroup6.add(radiosimp7);
        radiosimp7.setText("Sim");
        radiosimp7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp7ActionPerformed(evt);
            }
        });

        buttonGroup6.add(radionaop7);
        radionaop7.setText("Não");
        radionaop7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop7ActionPerformed(evt);
            }
        });

        labelp7.setText("Qual especialista?");

        jLabel34.setText("8- Já teve alguma doença que ache necessário registrar?");

        buttonGroup7.add(radiosimp8);
        radiosimp8.setText("Sim");
        radiosimp8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp8ActionPerformed(evt);
            }
        });

        buttonGroup7.add(radionaop8);
        radionaop8.setText("Não");
        radionaop8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop8ActionPerformed(evt);
            }
        });

        labelp8.setText("Qual ?");

        jLabel35.setText("9- Tem alguma restrição alimentar?");

        buttonGroup8.add(radiosimp9);
        radiosimp9.setText("Sim");
        radiosimp9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosimp9ActionPerformed(evt);
            }
        });

        buttonGroup8.add(radionaop9);
        radionaop9.setText("Não");
        radionaop9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radionaop9ActionPerformed(evt);
            }
        });

        labelp9.setText("Qual ?");

        jLabel8.setText("Observações:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        botaoAlterar.setBackground(new java.awt.Color(38, 38, 177));
        botaoAlterar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoAlterar.setForeground(java.awt.Color.white);
        botaoAlterar.setText("Alterar Dados");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(38, 38, 177));
        jButton1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jButton1.setForeground(java.awt.Color.white);
        jButton1.setText("Gerar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botaoExcluir.setBackground(new java.awt.Color(240, 5, 5));
        botaoExcluir.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoExcluir.setForeground(java.awt.Color.white);
        botaoExcluir.setText("Excluir Aluno");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoVoltar.setBackground(new java.awt.Color(38, 38, 177));
        botaoVoltar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoVoltar.setForeground(java.awt.Color.white);
        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });

        labelinf.setText("inf");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radiosimp1)
                                .addGap(18, 18, 18)
                                .addComponent(radionaop1)
                                .addGap(18, 18, 18)
                                .addComponent(labelinf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelp2)
                                    .addComponent(labelp1)
                                    .addComponent(labelp3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campop3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campop1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campop2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radiosimp7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radionaop7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                                        .addComponent(labelp7))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelp9)
                                            .addComponent(labelp8))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campop7, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campop8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campop9, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18)
                                        .addComponent(radiosimp9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radionaop9))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radiosimp8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radionaop8))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel29))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(radiosimp2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(radionaop2))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(radiosimp3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(radionaop3))))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel32)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(radiosimp6))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel31)
                                                .addGap(18, 18, 18)
                                                .addComponent(radiosimp5)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(radionaop5)
                                            .addComponent(radionaop6)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxcatapora)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBoxsarampo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBoxCaxumba)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBoxConvulsao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBoxDesmaio)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(botaoVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(radiosimp1)
                    .addComponent(radionaop1)
                    .addComponent(labelp1)
                    .addComponent(campop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelinf))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(radiosimp2)
                        .addComponent(radionaop2)
                        .addComponent(campop2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelp2))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(radiosimp3)
                        .addComponent(radionaop3))
                    .addComponent(campop3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelp3))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jCheckBoxcatapora)
                    .addComponent(jCheckBoxsarampo)
                    .addComponent(jCheckBoxCaxumba)
                    .addComponent(jCheckBoxConvulsao)
                    .addComponent(jCheckBoxDesmaio))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(radiosimp5)
                    .addComponent(radionaop5))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(radiosimp6)
                    .addComponent(radionaop6))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(campop7)
                    .addComponent(labelp7)
                    .addComponent(radionaop7)
                    .addComponent(radiosimp7))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(campop8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelp8)
                    .addComponent(radiosimp8)
                    .addComponent(radionaop8))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(radiosimp9)
                    .addComponent(radionaop9)
                    .addComponent(campop9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelp9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        jLabel1.setBackground(new java.awt.Color(38, 38, 177));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(38, 38, 177));
        jLabel1.setText("Matricula:");

        campoMatricula.setEditable(false);
        campoMatricula.setBackground(new java.awt.Color(38, 38, 177));
        campoMatricula.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        campoMatricula.setForeground(new java.awt.Color(38, 38, 177));
        campoMatricula.setEnabled(false);
        campoMatricula.setSelectedTextColor(new java.awt.Color(194, 24, 24));

        botaoVoltaN.setBackground(new java.awt.Color(38, 38, 177));
        botaoVoltaN.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoVoltaN.setForeground(java.awt.Color.white);
        botaoVoltaN.setText("Voltar");
        botaoVoltaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltaNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoVoltaN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoVoltaN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoTelefoneMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneMaeActionPerformed

    private void campoTelefonePaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefonePaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefonePaiActionPerformed

    private void radionaop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop1ActionPerformed
    labelp1.setVisible(true);
            campop1.setVisible(true);
            campop1.getText();
    }//GEN-LAST:event_radionaop1ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        buscarCep(campoCep.getText());
        preencheCampos();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       InserirFoto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void radionaop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop2ActionPerformed
         labelp2.setVisible(false);
          campop2.setVisible(false);
          campop2.setText("N");
      
    }//GEN-LAST:event_radionaop2ActionPerformed

    private void radionaop3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop3ActionPerformed
       labelp3.setVisible(false);
          campop3.setVisible(false);
          campop3.setText("N");
    }//GEN-LAST:event_radionaop3ActionPerformed

    private void radionaop5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop5ActionPerformed
         informacao.setPergunta5("N");

    }//GEN-LAST:event_radionaop5ActionPerformed

    private void radionaop6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop6ActionPerformed
        informacao.setPergunta6("N");

    }//GEN-LAST:event_radionaop6ActionPerformed

    private void radionaop7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop7ActionPerformed
        labelp7.setVisible(false);
        campop7.setVisible(false);
        campop7.setText("N");
    }//GEN-LAST:event_radionaop7ActionPerformed

    private void radiosimp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp1ActionPerformed
    labelp1.setVisible(false);
            campop1.setVisible(false);
            campop1.setText("S");
    }//GEN-LAST:event_radiosimp1ActionPerformed

    private void radionaop8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop8ActionPerformed
     labelp8.setVisible(false);
        campop8.setVisible(false);
        campop8.setText("N");
    }//GEN-LAST:event_radionaop8ActionPerformed

    private void radionaop9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radionaop9ActionPerformed
        labelp9.setVisible(false);
        campop9.setVisible(false);
        campop9.setText("N");
    }//GEN-LAST:event_radionaop9ActionPerformed

    private void radiosimp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp2ActionPerformed
    labelp2.setVisible(true);
    campop2.setVisible(true);
    campop2.getText();
    }//GEN-LAST:event_radiosimp2ActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        try {
            Alterar();
        } catch (Exception ex) {
            Logger.getLogger(AlunoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void radiosimp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp3ActionPerformed
        labelp3.setVisible(true);
        campop3.setVisible(true);
        campop3.getText();
    }//GEN-LAST:event_radiosimp3ActionPerformed

    private void radiosimp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp5ActionPerformed
         informacao.setPergunta5("S");

    }//GEN-LAST:event_radiosimp5ActionPerformed

    private void radiosimp6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp6ActionPerformed
        informacao.setPergunta6("S");

    }//GEN-LAST:event_radiosimp6ActionPerformed

    private void radiosimp7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp7ActionPerformed
       labelp7.setVisible(true);
        campop7.setVisible(true);
        campop7.getText();
    }//GEN-LAST:event_radiosimp7ActionPerformed

    private void radiosimp8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp8ActionPerformed
        labelp8.setVisible(true);
        campop8.setVisible(true);
        campop8.getText();
    }//GEN-LAST:event_radiosimp8ActionPerformed

    private void radiosimp9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiosimp9ActionPerformed
        labelp9.setVisible(true);
        campop9.setVisible(true);
        campop9.getText();
    }//GEN-LAST:event_radiosimp9ActionPerformed

    private void campop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campop1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campop1ActionPerformed

    private void campop3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campop3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campop3ActionPerformed

    private void jCheckBoxcataporaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxcataporaActionPerformed
       if(jCheckBoxcatapora.isSelected()){
            //texto += "Catapora ";
            texto.add("Catapora");
       }else{
            texto.remove("Catapora");  
       }
           
    }//GEN-LAST:event_jCheckBoxcataporaActionPerformed

    private void jCheckBoxsarampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxsarampoActionPerformed
     if(jCheckBoxsarampo.isSelected()){
     texto.add("Sarampo");
      }else{
            texto.remove("Sarampo"); 
       }
    }//GEN-LAST:event_jCheckBoxsarampoActionPerformed

    private void jCheckBoxCaxumbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCaxumbaActionPerformed
        if(jCheckBoxCaxumba.isSelected()){
            texto.add("Caxumba");
         }else{
            texto.remove("Caxumba");
       }
    }//GEN-LAST:event_jCheckBoxCaxumbaActionPerformed

    private void jCheckBoxConvulsaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxConvulsaoActionPerformed
       if(jCheckBoxConvulsao.isSelected()){
            texto.add("Convulsão");
             }else{
            texto.remove("Convulsão");
       }
    }//GEN-LAST:event_jCheckBoxConvulsaoActionPerformed

    private void jCheckBoxDesmaioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDesmaioActionPerformed
        if(jCheckBoxDesmaio.isSelected()){
            texto.add("Desmaio");
         }else{
            texto.remove("Desmaio");
       }
    }//GEN-LAST:event_jCheckBoxDesmaioActionPerformed

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        try {
            new AlunoPesquisaTela(acesso).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(AlunoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoVoltarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
              int resposta=JOptionPane.showConfirmDialog(this,"Deseja realmente excluir "+aluno.getNome()+"?",
                                                 "Exclusão de Aluno",JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE);
        if(resposta==JOptionPane.YES_OPTION)
        try{
            deletar();
            JOptionPane.showMessageDialog(this, aluno.getNome()+" excluido") ;
            dispose();
            new AlunoPesquisaTela(acesso).setVisible(true);
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Erro:"+ e.getMessage());
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        relatorio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoVoltaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltaNActionPerformed
        try {
            new AlunoPesquisaTela(acesso).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(AlunoAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoVoltaNActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AlunoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AlunoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AlunoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AlunoAlteraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AlunoAlteraTela().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoVoltaN;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroupMoraCrianca;
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JFormattedTextField campoCep;
    private javax.swing.JTextField campoLocalTrabalhoMae;
    private javax.swing.JTextField campoLocalTrabalhoPai;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JTextField campoNaturalidade;
    private javax.swing.JTextField campoNomeAluno;
    private javax.swing.JTextField campoNomeMae;
    private javax.swing.JTextField campoNomePai;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JTextField campoProfissaoMae;
    private javax.swing.JTextField campoProfissaoPai;
    private javax.swing.JTextField campoRua;
    private javax.swing.JFormattedTextField campoTelefoneMae;
    private javax.swing.JFormattedTextField campoTelefonePai;
    private javax.swing.JFormattedTextField campoTelefoneTrabalhoMae;
    private javax.swing.JFormattedTextField campoTelefoneTrabalhoPai;
    private javax.swing.JTextField campop1;
    private javax.swing.JTextField campop2;
    private javax.swing.JTextField campop3;
    private javax.swing.JTextField campop7;
    private javax.swing.JTextField campop8;
    private javax.swing.JTextField campop9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxCaxumba;
    private javax.swing.JCheckBox jCheckBoxConvulsao;
    private javax.swing.JCheckBox jCheckBoxDesmaio;
    private javax.swing.JCheckBox jCheckBoxcatapora;
    private javax.swing.JCheckBox jCheckBoxsarampo;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxTurma;
    private javax.swing.JComboBox<String> jComboBoxTurno;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonF;
    private javax.swing.JRadioButton jRadioButtonM;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelinf;
    private javax.swing.JLabel labelp1;
    private javax.swing.JLabel labelp2;
    private javax.swing.JLabel labelp3;
    private javax.swing.JLabel labelp7;
    private javax.swing.JLabel labelp8;
    private javax.swing.JLabel labelp9;
    private javax.swing.JRadioButton radionaop1;
    private javax.swing.JRadioButton radionaop2;
    private javax.swing.JRadioButton radionaop3;
    private javax.swing.JRadioButton radionaop5;
    private javax.swing.JRadioButton radionaop6;
    private javax.swing.JRadioButton radionaop7;
    private javax.swing.JRadioButton radionaop8;
    private javax.swing.JRadioButton radionaop9;
    private javax.swing.JRadioButton radiosimp1;
    private javax.swing.JRadioButton radiosimp2;
    private javax.swing.JRadioButton radiosimp3;
    private javax.swing.JRadioButton radiosimp5;
    private javax.swing.JRadioButton radiosimp6;
    private javax.swing.JRadioButton radiosimp7;
    private javax.swing.JRadioButton radiosimp8;
    private javax.swing.JRadioButton radiosimp9;
    // End of variables declaration//GEN-END:variables

   
}

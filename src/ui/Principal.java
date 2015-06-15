package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.GZIPOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Esta classe representa um programa para compactar arquivos
 * 
 * @author Adriano Barbosa Quaresma
 * @author Weber Pereira
 * @Author Thiago Alves
 * @version 1.0
 * @since 1.5
 * @date 11/06/2015
 */
public class Principal extends JFrame {

	JFileChooser procurar;
	private JFrame janela;
	private JLabel editadoPanel, icon, Status, texto1, texto2;
	private JButton botaoAbrir, botaoCompactar;
	private JTable tabela;
	private JScrollPane verTabela;
	JTextField dir;
	File arquivo;

	// protected java.applet.AudioClip Musica = null;

	// Metodo Main que captura a Tela
	public static void main(String[] args) {

		Principal principal = new Principal();
		principal.tela();

	}

	// Metodo onde estará os construtores agrupados para o Main capturar
	private void tela() {

		editadoFrame("ProZip");
		editadoPanel();
		botaoAbrir("PROCURAR");
		botaoCompactar("COMPACTAR");
		Status("Status");
		espaçoTexto();
		verPanel();
		verTabela();
		tabela();
		vertexto();
		verFrame();

	}

	// Metodo da descrição dos botões do Frame
	private void vertexto() {
		texto1 = new JLabel("SELECIONE O ARQUIVO");
		texto1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		texto1.setBounds(250, 40, 390, 30);
		texto2 = new JLabel("CLIQUE PARA COMPACTAR");
		texto2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		texto2.setBounds(250, 105, 390, 30);

		janela.add(texto1);
		janela.add(texto2);
	}

	// Criação e configuração do Frame
	private void editadoFrame(String titulo) {

		janela = new JFrame(titulo);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// janela.setBackground(Color.BLUE);
		janela.setLayout(null);
		// janela.setLocationRelativeTo(null);
		janela.setResizable(false);
	}

	// Mais configurações do Frame
	private void verFrame() {

		janela.setLayout(null);
		janela.setSize(600, 400);
		janela.setVisible(true);
		// Musica.play();

	}

	// Criação do Botão Abrir e suas configurações
	private void botaoAbrir(String titulo) {

		botaoAbrir = new JButton(titulo);
		botaoAbrir.setBounds(460, 30, 110, 50);
		botaoAbrir.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		botaoAbrir.addActionListener(new ActionListener() {

			// Evento do Botão Abrir
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buscaArquivo();
			}
		});
		janela.add(botaoAbrir);

	}

	// Metodo para escolha do arquivo
	private void buscaArquivo() {

		procurar = new JFileChooser();
		dir.setText("Procurando Arquivo");

		int retorno = procurar.showOpenDialog(null);
		System.out.printf("Retorno = %d\n", retorno);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			// Colocar o código de compactação do arquivo aqui.
			System.out.printf("Origem =  %s\n", procurar.getSelectedFile()
					.getName());
			JOptionPane
					.showMessageDialog(null,
							"Arquivo carregado com SUCESSO! Clique em COMPACTAR para zipar seu arquivo");

			// Caminho exibido na Barra de Status na hora que escolhe o arquivo
			dir.setText(procurar.getSelectedFile().getAbsolutePath());

		} else {
			System.out.printf("Operação Cancelada");
			// Mudança na cor no Status na hora que cancela a operação
			dir.setBackground(Color.RED);
			dir.setText("Operação Cancelada");

		}

	}

	// Criação do Botão Compactar
	public void botaoCompactar(String titulo) {

		botaoCompactar = new JButton(titulo);
		botaoCompactar.setBounds(460, 90, 110, 50);
		botaoCompactar.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		botaoCompactar.addActionListener(new ActionListener() {

			// Evento do Botão Compactar
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					zipaArquivo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		janela.add(botaoCompactar);
	}

	// Metodo para compactar o arquivo escolhido
	public void zipaArquivo() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				procurar.getSelectedFile()));
		BufferedOutputStream bos = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream(
						"C:/Users/Adm/Desktop/Algoritimo/ZipPro/compactados/Testando 1.gz"
								)));
		System.out
				.println("Destino = C:/Users/Adm/Desktop/Algoritimo/ZipPro/compactados" );

		// A hora atrelada a barra de Status quando finaliza a compactação
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		dir.setText("Seu Arquivo Foi Compactado as "
				+ sdf.format(Calendar.getInstance().getTime()));
		dir.setBackground(Color.GREEN);

		try {

			int a;
			while ((a = br.read()) != -1) {
				bos.write(a);
			}

			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
			bos.close();

		}

		// Metodo para listar os arquivos que contem na Pasta
		File zipaArquivo = new File(
				"C:/Users/Adm/Desktop/Algoritimo/ZipPro/compactados");
		File[] file = zipaArquivo.listFiles();

		if (file != null) {
			int length = file.length;

			for (int i = 0; i < length; ++i) {
				File f = file[i];

				if (f.isFile()) {
					System.out.println(f.getName());
				}

				else if (f.isDirectory()) {

					System.out.println("Diretorio: " + f.getName());
				}

			}
		}
	}

	// Metodo onde joga a figura no Frame
	private void verPanel() {
		janela.add(editadoPanel);
	}

	// Metodo de criação e configuração do JLabel que se encontra o LOGO do
	// programa
	private void editadoPanel() {

		editadoPanel = new JLabel();
		editadoPanel.setBounds(20, 10, 150, 130);
		editadoPanel.setIcon(new ImageIcon("logomarca.gif"));
	}

	// Criação da tabela e suas respectivas colunas e linhas
	private void tabela() {

		String[] colunas = { "LISTA DE ARQUIVOS", };
		Object[][] dados = { { "", "", "", }, { "", "", "", }, { "", "", "", },
				{ "", "", "", }, { "", "", "", }, { "", "", "", },
				{ "", "", "", }, { "", "", "", }, { "", "", "", },
				{ "", "", "", }, { "", "", "", }, { "", "", "", },
				{ "", "", "", }, { "", "", "", }, { "", "", "", }, };
		tabela = new JTable(dados, colunas);
		verTabela.setViewportView(tabela);
	}

	// Metodo onde joga a tabela dentro de um JScrollPane e suas configurações
	private void verTabela() {
		verTabela = new JScrollPane(tabela);
		verTabela.setBounds(20, 155, 550, 180);

		janela.add(verTabela);
	}

	// Método de criação do JTextField para mudanças do Status do programa
	private void espaçoTexto() {
		dir = new JTextField();
		dir.setBounds(105, 343, 450, 25);
		dir.setBackground(Color.LIGHT_GRAY);
		janela.add(dir);

	}

	// Criação do Status
	private void Status(String titulo) {

		Status = new JLabel(titulo);
		Status.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		Status.setBounds(20, 340, 200, 30);
		Status.setForeground(Color.BLUE);

		janela.add(Status);
	}
}

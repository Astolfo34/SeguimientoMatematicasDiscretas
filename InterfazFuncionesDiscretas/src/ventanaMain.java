

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ventanaMain extends JFrame {

	private JPanel contentPane;
	private JTextField txtConjuntoA;
	private JTextField txtConjuntoB;
	private JTextField txtFuncion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaMain frame = new ventanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	////////////////////////////////////////////
	public static int[] limpiarConjunto(String conjunto) {/////1,2,3,4  solo limpia numeros separados por comas

		char conjuntoChar[]=conjunto.toCharArray();
		///limpiar las comas
		int cont=0;
		int vectorPrueba[]=new int [conjuntoChar.length];
		for(int i=0;i<conjuntoChar.length;i++) {
			if(conjuntoChar[i]!=','&&conjuntoChar[i]!='('&&conjuntoChar[i]!=')') {
				
				vectorPrueba[i] = Character.getNumericValue(conjuntoChar[i]);
				
			}
			if(vectorPrueba[i]!=0) {
				cont++;
			}
		}
		int vector[]=new int [cont];
		int b=0;
		for(int k=0;k<vectorPrueba.length;k++) {
			if(vectorPrueba[k]!=0) {
			vector[b]=vectorPrueba[k];
			b++;
			}
		}
		
		
		
		return vector;
	}
	
	public static String ConvertitArregloEnteroString(int arreglo[]) {
		  String resultado="";
		  for(int i=0;i<arreglo.length;i++) {
			  resultado=resultado+arreglo[i];
		  }
		return resultado;  
	  }
	public static int[]rangoUsado(String conjuntoFuncion){
		int tam=(limpiarConjunto(conjuntoFuncion).length);
		int funLimpia[]=new int[ tam ];
		funLimpia=limpiarConjunto(conjuntoFuncion);
		int tomadosX[]=new int[ (funLimpia.length)];
		int tomadosY[]=new int[ (funLimpia.length)];

		for(int i=0;i<funLimpia.length;i++) {
			if(i%2==0) {
				tomadosX[i]=funLimpia[i];//FUNCIONA PERO HAY QUE ELIMINAR LOS VACIOS QUE TIENEN 0
			}else {
				tomadosY[i]=funLimpia[i];
			}
			
		}
		///proceso que quita ceros
		
		int temp=0;
		int k=0;
		int y[]=new int[(funLimpia.length/2)];
				for(int j=0;j<funLimpia.length;j++) {
					temp=funLimpia[j];
					if(tomadosY[j]!=0) {
					y[k]=temp;
					k++;
					}
				}


		return y;
	}
	
	public static boolean determinarInyectiva(int ca[],int cb[],String conjunto,String conjuntoFuncion) {
		int[] resultado=new int [(ca.length)+(cb.length)];
		int repeticiones=0;
		boolean inyectiva=false;
		int y[]=new int[rangoUsado(conjuntoFuncion).length];
		y=rangoUsado( conjuntoFuncion);
		int tomadosY[]=new int[EliminarRepetidos(y).length];
		tomadosY=EliminarRepetidos(y);
		//validar datos de relacion 
		for(int i=0;i<cb.length;i++) {
			for(int j=0;j<y.length;j++) {
				if(cb[i]==y[j]) {
					repeticiones++;
				}
			}
		}
		
		if(tomadosY.length==ca.length ||tomadosY.length>ca.length &&  (sonArraysIguales(cb,y))==true) {
			inyectiva=true;
		}
		
		
		return inyectiva;
	}
	/////ELIMINAR REPETICIONES/////////////////
	public static int[] EliminarRepetidos(int[]arreglo){
		   Arrays.sort(arreglo);///metodo para ordenar un arreglo de enteros de menor a mayor
		   int len = arreglo.length;
		   int j = 0;
		   for(int i=0;i<len-1;i++){
		       if(arreglo[i]!=arreglo[i+1]){
		          arreglo[j++] = arreglo[i];
		       }
		   }
		   arreglo[j++] = arreglo[len-1];
		   int [] c = new int[j];
		   for(int k = 0;k<j;k++){
		   c[k] = arreglo[k];
		   }
		   return c;
		   }

	
	public static boolean determinarSobreyectiva(int RelacionesRangoY[],int codominio[]) {
		boolean sobreyectiva=false;
		int rangoY[]=new int[codominio.length];
		
		int codominioB[]=new int[codominio.length];
		codominioB=OrdenarConAcendente(codominio);
		rangoY= OrdenarConAcendente(EliminarRepetidos(RelacionesRangoY));
		
		
		
		if(sonArraysIguales(rangoY, codominioB)) {
			sobreyectiva=true;
		}
		
		
		return sobreyectiva;
	}
	
	
	
	public static boolean sonArraysIguales(int[] array1, int[] array2){
        boolean iguales = (array1.length==array2.length);
        if(iguales){
            for(int i=0;i<array1.length && iguales;i++){
                if(array1[i]!=array2[i]){
                    iguales = false;
                }
            }
        }
        return iguales;
    }
	
public static boolean determinarBiyectiva(boolean inyectiva,boolean sobreyectiva) {
	boolean biyectivo=false;
	
	if(inyectiva==true && sobreyectiva==true) {
		biyectivo=true;
	}
	
	return biyectivo;
}
/////////////METODO BURBUJA
public static int [] OrdenarConAcendente(int arregloA[]){//ordena acendente
	int tam=arregloA.length;
	int array[]=new int[tam];
	for(int i=0;i<arregloA.length;i++) {
		array[i]=arregloA[i];
	}
	
	int arregloUP[]=array;
	for(int i=0;i<arregloUP.length;i++) {
		for(int j=0;j<arregloUP.length-1;j++) {
			if(arregloUP[j]>arregloUP[j+1]) {
				
				int temp =arregloUP[j];
				arregloUP[j]=arregloUP[j+1];
				arregloUP[j+1]=temp;
				
				
			}
		}
	}
	
	return arregloUP;
}
	////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Create the frame.
	 */
	public ventanaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTituloPrograma = new JLabel("DETERMINAR TIPO DE FUNCION");
		lblTituloPrograma.setFont(new Font("Mongolian Baiti", Font.PLAIN, 17));
		lblTituloPrograma.setBounds(52, 11, 294, 30);
		contentPane.add(lblTituloPrograma);
		
		txtConjuntoA = new JTextField();
		txtConjuntoA.setBounds(10, 67, 290, 20);
		contentPane.add(txtConjuntoA);
		txtConjuntoA.setColumns(10);
		
		txtConjuntoB = new JTextField();
		txtConjuntoB.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtConjuntoB.setBounds(10, 142, 290, 20);
		contentPane.add(txtConjuntoB);
		txtConjuntoB.setColumns(10);
		
		JLabel lblInstruccionA = new JLabel("Ingrese elementos del conjunto de Partida (A)\r\nseparados por comas eje: 1,2,3");
		lblInstruccionA.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblInstruccionA.setBounds(10, 44, 456, 20);
		contentPane.add(lblInstruccionA);
		
		JLabel lblNewLabel = new JLabel("Ingrese elementos del conjunto de llegada (B)\r\nseparados por comas eje: 1,2,3");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 98, 445, 33);
		contentPane.add(lblNewLabel);
		
		txtFuncion = new JTextField();
		txtFuncion.setBounds(10, 204, 290, 20);
		contentPane.add(txtFuncion);
		txtFuncion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese la funcion-----> eje: (1,2)(2,3)(3,4)(4,5)");
		lblNewLabel_1.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 173, 420, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCalcular = new JButton("CALCULAR");
		btnCalcular.setFont(new Font("Snap ITC", Font.ITALIC, 16));
		
		JLabel lblResultadoInjectiva = new JLabel("=");
		lblResultadoInjectiva.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblResultadoInjectiva.setBounds(24, 315, 114, 25);
		contentPane.add(lblResultadoInjectiva);
		
		JLabel lblResultadoSobrejectiva = new JLabel("=");
		lblResultadoSobrejectiva.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblResultadoSobrejectiva.setBounds(200, 315, 115, 30);
		contentPane.add(lblResultadoSobrejectiva);
		
		JLabel lblResultadoBijectiva = new JLabel("=");
		lblResultadoBijectiva.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblResultadoBijectiva.setBounds(344, 320, 122, 25);
		contentPane.add(lblResultadoBijectiva);
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//////////////////////////////MINI MAIN//////////////////////////////	
				String conjuntoA=txtConjuntoA.getText();
				String conjuntoB=txtConjuntoB.getText();
				String conjuntoFuncion=txtFuncion.getText();
				
				String conjuntoAlimpio=ConvertitArregloEnteroString(limpiarConjunto(conjuntoA));
				int limpioA[]=new int[conjuntoAlimpio.length()];
				limpioA=limpiarConjunto(conjuntoA);
				
				String conjuntoBlimpio=ConvertitArregloEnteroString(limpiarConjunto(conjuntoB));
				int limpioB[]=new int [conjuntoBlimpio.length()];
				limpioB=limpiarConjunto(conjuntoB);
				
				String funcionLimpia=ConvertitArregloEnteroString(limpiarConjunto(conjuntoFuncion));
				String vectorRelacionY=ConvertitArregloEnteroString(rangoUsado(funcionLimpia));
				int relacionesEnY[]=new int[vectorRelacionY.length()];
				relacionesEnY=rangoUsado(funcionLimpia);
				
				
				if(determinarInyectiva(limpioA, limpioB, conjuntoBlimpio, conjuntoFuncion)==true) {
					lblResultadoInjectiva.setText(""+"es injectiva");
				}else {
					lblResultadoInjectiva.setText(""+"no es injectiva");
				}
				
				if(determinarSobreyectiva(relacionesEnY, limpioB)) {
					lblResultadoSobrejectiva.setText(""+"es sobreyectiva");
				}else {
					lblResultadoSobrejectiva.setText(""+"no es sobreyectiva");
				}
				
				boolean inyectiva=determinarInyectiva(limpioA, limpioB, conjuntoBlimpio, conjuntoFuncion);
				boolean sobreyectivo=determinarSobreyectiva(relacionesEnY, limpioB);
				if(determinarBiyectiva(inyectiva, sobreyectivo)==true) {
					lblResultadoBijectiva.setText(""+"es biyectiva");
				}else {
					lblResultadoBijectiva.setText(""+"no es biyectiva");
				}
			}
		});
		btnCalcular.setBounds(165, 247, 181, 43);
		contentPane.add(btnCalcular);
		
		JButton btnNewButton = new JButton("LIMPIAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtConjuntoA.setText("");	
				txtConjuntoB.setText("");
				txtFuncion.setText("");
				lblResultadoInjectiva.setText("");
				lblResultadoSobrejectiva.setText("");
				lblResultadoBijectiva.setText("");
			}
		});
		btnNewButton.setFont(new Font("Rockwell Condensed", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(344, 158, 105, 78);
		contentPane.add(btnNewButton);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

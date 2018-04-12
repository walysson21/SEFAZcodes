import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public final class Imagem {

    public static final String JPEG = "JPEG";
    public static final String PNG = "PNG";
    public static final int GRAY = BufferedImage.TYPE_BYTE_GRAY;
    public static final int RGB = BufferedImage.TYPE_INT_RGB;
    private BufferedImage image = null;
    private WritableRaster raster = null;
    private int tipo;
    private int altura;
    private int largura;

    public Imagem(String path) {
        try {
            image = ImageIO.read(new File(path));
            raster = image.getRaster();
            tipo = image.getType();
            altura = image.getHeight();
            largura = image.getWidth();
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Imagem(File arquivo) {
        try {
            image = ImageIO.read(arquivo);
            raster = image.getRaster();
            tipo = image.getType();
            altura = image.getHeight();
            largura = image.getWidth();
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Imagem(BufferedImage bufferedImage) {
        image = bufferedImage;
        raster = bufferedImage.getRaster();
        tipo = bufferedImage.getType();
        altura = bufferedImage.getHeight();
        largura = bufferedImage.getWidth();
    }

    public Imagem(int altura, int largura, int tipo) {
        criar(altura, largura, tipo);
    }

    public Imagem(int[] vetor, int alt, int larg) {
        largura = larg;
        altura = alt;
        image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        raster = image.getRaster();
        int pos, cor;
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                pos = j + i * larg;
                cor = vetor[pos];
                setPixel(i, j, cor, cor, cor);
            }
        }
    }

    public Imagem(int[][] matriz) {
        altura = matriz.length;
        largura = matriz[0].length;
        image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        raster = image.getRaster();
        int cor;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                cor = matriz[i][j];
                setPixel(i, j, cor, cor, cor);
            }
        }
    }

    public Imagem(short[][] matriz) {
        altura = matriz.length;
        largura = matriz[0].length;
        image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        raster = image.getRaster();
        int cor;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                cor = matriz[i][j];
                setPixel(i, j, cor, cor, cor);
            }
        }
    }

    public final void criar(int altura, int largura, int tipo) {
        image = new BufferedImage(largura, altura, tipo);
        raster = image.getRaster();
        this.tipo = tipo;
        this.altura = altura;
        this.largura = largura;
    }

    public void setBuffer(BufferedImage buf) {
        this.image = buf;
        this.altura = buf.getHeight();
        this.largura = buf.getWidth();
    }

    public BufferedImage toGray() {
        Imagem retorno = new Imagem(altura, largura, BufferedImage.TYPE_BYTE_GRAY);
        int r, g, b;
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                r = getPixel(y, x, 0);
                g = getPixel(y, x, 1);
                b = getPixel(y, x, 2);
                r = (r + g + b) / 3;
                retorno.setPixel(y, x, r, 0, 0);
            }
        }
        return retorno.getBuffer();
    }

    public BufferedImage toRGB() {
        Imagem retorno = new Imagem(altura, largura, BufferedImage.TYPE_INT_RGB);
        int r;
        int g;
        int b;
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                r = getPixel(y, x, 0);
                g = getPixel(y, x, 1);
                b = getPixel(y, x, 2);
                retorno.setPixel(y, x, r, g, b);
            }
        }
        return retorno.getBuffer();
    }

    public BufferedImage getBuffer() {
        return image;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public int getPixel(int y, int x, int matriz) {
        return raster.getSample(x, y, matriz);
    }

    public int getPixel(float y, float x, int matriz) {
        return getPixel((int) y, (int) x, matriz);
    }

    public double getAreaTotal() {
        return altura * largura;
    }

    public short[][] getMatriz(int canal) {
        short m[][] = new short[altura][largura];
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                m[y][x] = (short) getPixel(y, x, canal);
            }
        }
        return m;
    }

    public void setPixel(int y, int x, int r, int g, int b) {
        int pixel[] = {r, g, b};
        raster.setPixel(x, y, pixel);
    }

    public void setPixel(float y, float x, int r, int g, int b) {
        int pixel[] = {r, g, b};
        raster.setPixel((int) x, (int) y, pixel);
    }

    public void salvar(String nome, String tipoSaida) {
        try {
            ImageIO.write(image, tipoSaida, new File(nome));
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrar(String titulo) {
        new IFrame(titulo, this);
    }

    public void mostrar() {
        new IFrame("", this);
    }

    public int[] toArray() {
        int array[] = new int[altura * largura];
        int i = 0;
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                array[i++] = getPixel(y, x, 0);
            }
        }
        return array;
    }

    @Override
    public Imagem clone() {
        Imagem clone = new Imagem(altura, largura, tipo);
        int r, g, b;

        if (tipo == RGB) {
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    r = this.getPixel(y, x, 0);
                    g = this.getPixel(y, x, 1);
                    b = this.getPixel(y, x, 2);
                    clone.setPixel(y, x, r, g, b);
                }
            }
        }
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                r = this.getPixel(y, x, 0);
                clone.setPixel(y, x, r, 0, 0);
            }
        }
        return clone;
    }
    
    public Imagem somar(Imagem imagem) {
    	int altura = imagem.getAltura();
    	int largura = imagem.getLargura();
    	int area = altura*largura;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = imagem.getPixel(i, j, 0) + this.getPixel(i, j, 0);
    			vetor[pos] = valor;
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
    
    public Imagem subtrair(Imagem imagem) {
    	int altura = imagem.getAltura();
    	int largura = imagem.getLargura();
    	int area = altura*largura;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = imagem.getPixel(i, j, 0) - this.getPixel(i, j, 0);
    			vetor[pos] = valor;
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
    
    public Imagem multiplicar(Imagem imagem) {
    	int altura = imagem.getAltura();
    	int largura = imagem.getLargura();
    	int area = altura*largura;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = imagem.getPixel(i, j, 0) * this.getPixel(i, j, 0);
    			if(valor > 255)
    				vetor[pos] = 255;
    			else
    				vetor[pos] = valor;
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
    
    public Imagem dividir(Imagem imagem) {
    	int altura = imagem.getAltura();
    	int largura = imagem.getLargura();
    	int area = altura*largura;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = 255;
    			
    			if(this.getPixel(i, j, 0) == 0) {
    				vetor[pos] = 255;
    			} else {
    				valor = (int) (imagem.getPixel(i, j, 0) / this.getPixel(i, j, 0));
    				vetor[pos] = valor;
    			}
    				
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
    
    public Imagem media(Imagem imagem) {
    	int altura = imagem.getAltura();
    	int largura = imagem.getLargura();
    	int area = altura*largura;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = (int) (imagem.getPixel(i, j, 0) + this.getPixel(i, j, 0))/2;
    			vetor[pos] = valor;
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
    
    public Imagem not() {
    	int altura = this.getAltura();
    	int largura = this.getLargura();
    	int area = altura*largura;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = 255 - this.getPixel(i, j, 0);
    			vetor[pos] = valor;
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
    
    public Imagem or(Imagem imagem) {
    	return somar(imagem);
    }
    
    public Imagem and(Imagem imagem) {
    	return multiplicar(imagem);
    }
    
    public Imagem xor(Imagem imagem) {
    	Imagem im1 = this.and( imagem.not() );
    	Imagem im2 = imagem.and( this.not() );
    	return im1.or(im2);
    }
    
    public Imagem convolucao(int convDim) {
    	int altura = this.getAltura() - convDim - 1;
    	int largura = this.getLargura() - convDim - 1;
    	int area = altura * largura ;
    	int vetor[] = new int[area];
    	
    	for (int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			int pos = j + i * largura;
    			int valor = 0;
    			
    			for (int k = 0; k < convDim; k++) {
    				for (int l = 0; l < convDim; l++) {
    					valor +=  this.getPixel(i+k, j+l, 0);
    				}
				}
    			
    			vetor[pos] = (int) valor/(convDim * convDim);
    		}
		}
    	
    	return new Imagem(vetor, altura, largura);
    }
}

class IFrame extends JFrame {

    private static final long serialVersionUID = 4074185320511690112L;
    private int xIni = 0;
    private int yIni = 0;
    private int xFim = 0;
    private int yFim = 0;
    JScrollPane scroll = null;

    @SuppressWarnings("serial")
	class GCanvas extends JPanel {

        private Imagem img;

        public GCanvas(Imagem img) {
            this.img = img;
            setSize(getPreferredSize());
            this.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    scrollMouseClicked(evt);
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                    scrollMousePressed(evt);
                }

                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    scrollMouseReleased(evt);
                }
            });
        }

        public Dimension getPreferredSize() {
            return new Dimension(img.getLargura(), img.getAltura());
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img.getBuffer(), 0, 0, this);
        }

        public void scrollMouseClicked(MouseEvent e) {}

        public void scrollMousePressed(MouseEvent e) {
            xIni = e.getX();
            yIni = e.getY();
        }

        public void scrollMouseReleased(MouseEvent e) {
            xFim = e.getX();
            yFim = e.getY();
            int dx = xFim - xIni;
            int dy = yFim - yIni;
            int posAtual = scroll.getHorizontalScrollBar().getValue();
            scroll.getHorizontalScrollBar().setValue(posAtual + dx);
            posAtual = scroll.getVerticalScrollBar().getValue();
            scroll.getVerticalScrollBar().setValue(posAtual + dy);
        }

        public void scrollMouseEntered(MouseEvent e) { }

        public void scrollMouseExited(MouseEvent e) { }
    }

    public IFrame(String titulo, Imagem img, int altura, int largura) //constructor
    {
    }

    public IFrame(String titulo, Imagem img) //constructor
    {
        super(titulo);
        scroll = new JScrollPane(new GCanvas(img));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setMaximumSize(new Dimension(img.getLargura(),img.getAltura()));
        getContentPane().add(scroll);
        setSize(img.getLargura(), img.getAltura()+42);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
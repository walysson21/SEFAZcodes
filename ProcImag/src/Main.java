public class Main {
    
	public static void main(String args[]){
        Imagem imgA = new Imagem("A.PNG");
        Imagem imgB = new Imagem("B.PNG");
        Imagem imgC = imgA.somar(imgB);
        Imagem imgD = imgA.subtrair(imgB);
        Imagem imgE = imgB.multiplicar(imgA);
        Imagem imgF = imgB.dividir(imgA);
        Imagem imgG = imgA.dividir(imgB);
        Imagem imgH = imgA.media(imgB);
        Imagem imgI = imgB.not();
        Imagem imgJ = imgA.xor(imgB);
        Imagem imgK = imgB.convolucao(7);
        
//        imgA.mostrar("Imagem A");
//        imgB.mostrar("Imagem B");
//        imgC.mostrar("Imagem C");
//        imgD.mostrar("Imagem D");
//        imgE.mostrar("Imagem E");
//        imgF.mostrar("Imagem F");
//        imgG.mostrar("Imagem G");
//        imgH.mostrar("Imagem H");
//        imgI.mostrar("Imagem I");
//        imgJ.mostrar("Imagem J");
        imgK.mostrar("Imagem K");
    	
//    	int[] a = new int[65025];
//    	
//    	
//    	for (int i = 0; i < 255; i++) {
//    		for (int j = 0; j < 255; j++) {
//    			int pos = j + i*255;
//    			a[pos] = i;
//    		}
//		}
//    	
//    	Imagem img = new Imagem(a, 255, 255);
//        img.mostrar("Imagem A");
    }

}

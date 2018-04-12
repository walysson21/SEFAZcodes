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
        
        imgA.mostrar("A");
        imgB.mostrar("B");
        imgC.mostrar("SOMA");
        imgD.mostrar("SUBTRAÇÃO");
        imgE.mostrar("MULTIPLICAÇÃO");
        imgF.mostrar("IMG_A/IMG_b");
        imgG.mostrar("IMG_B/IMG_A");
        imgH.mostrar("MEDIA");
        imgI.mostrar("NOT B");
        imgJ.mostrar("XOR");
        imgK.mostrar("CONVOLUÇÃO B");
    
	}

}

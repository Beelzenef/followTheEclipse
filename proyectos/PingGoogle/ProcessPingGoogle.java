import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ProcessPingGoogle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String pingGoogle = "ping www.google.es";

		ProcessBuilder pb = new ProcessBuilder(pingGoogle);
		pb.redirectErrorStream(true);
		
		try {
			Process procesoHijo = pb.start();
			leerMsgsPing(procesoHijo);
			System.exit(0);
		}
		catch (Exception e)
		{
			System.err.println("Error IO");
			System.exit(-1);
		}
	}
	
	static void leerMsgsPing(Process p)
	{
		String msgs = new String();
		
		// Leyendo mensajes de ping
		try {
			InputStreamReader lectorInfo = new InputStreamReader(p.getInputStream(), "utf-8");
			BufferedReader lectorBuffer = new BufferedReader(lectorInfo);
			
			for (int i = 0; i < 5; i++) {
				msgs.concat(lectorBuffer.readLine());
				
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// Destruyendo proceso hijo, final de lectura de msgs
		if (p != null)
		{
			p.destroy();
		}
		
		// Salida correcta
		try {
			p.wait();
			System.out.println("Salida del proceso hijo: " + p.exitValue());
			System.exit(0);
		}
		catch (InterruptedException e)
		{
			System.out.println("Espera interrumpida");
		}
		
	}
	

}

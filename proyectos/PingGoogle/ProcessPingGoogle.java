import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ProcessPingGoogle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ping = "/bin/ping";
		String google = "www.google.es";

		ProcessBuilder pb = new ProcessBuilder(ping, google);
		pb.redirectErrorStream(true);
		Process procesoHijo;
		
		try {
			procesoHijo = pb.start();

			String msgs = new String();
			
			// Leyendo mensajes de ping
			InputStreamReader lectorInfo = new InputStreamReader(procesoHijo.getInputStream(), "utf-8");
			BufferedReader lectorBuffer = new BufferedReader(lectorInfo);
				
			for (int i = 0; i < 5; i++) {
				System.out.println(lectorBuffer.readLine());
			}	

			
			// Destruyendo proceso hijo, final de lectura de msgs
			if (procesoHijo != null)
			{
				procesoHijo.destroy();
			}
			
		}
		catch (Exception e)
		{
			System.err.println("Error IO");
			System.exit(-1);
		}
	}
	

}

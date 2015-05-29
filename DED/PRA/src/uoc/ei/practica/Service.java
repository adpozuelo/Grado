package uoc.ei.practica;

import java.util.Calendar;
import java.util.Date;

/**
 * método que representa un Servicio en el sistema
 *
 */
public class Service {

	/**
	 * usuario que realiza el servicio
	 */
	private User user;
	
	/**
	 * fecha de inicio del servicio
	 */
	private Date startTime;
	
	/**
	 * fecha de final del servicio
	 */
	private Date endTime;
	
	/**
	 * estación de origen
	 */
	private Station fromStation;
	
	/**
	 * estación destíno
	 */
	private Station toStation;

	public Service(User user, Station fromStation, Date startTime) {
		this.user=user;
		this.startTime=startTime;
		this.fromStation=fromStation;
	}

	/**
	 * método que notifica el fin de un servicio
	 * @param toStation estación destino
	 * @param endTime fecha en la que es retorna la bicicleta
	 * @return
	 */
	public long finish(Station toStation, Date endTime) {
		this.toStation=toStation;
		this.endTime=endTime;
		
		Calendar start = Calendar.getInstance();
		start.setTime(startTime);
		
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		
		return (end.getTimeInMillis()-start.getTimeInMillis());
	}

	/**
	 * método que proporciona una representación en forma de un String del Servicio
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("user: ").append(this.user.getName()).append(" ("+this.user.getIdentifier()+") ").append(Messages.LS);
		sb.append("from: ").append(this.fromStation.getIdentifier()).append(Messages.LS);
		if (this.toStation!=null) sb.append("to: ").append(this.toStation.getIdentifier()).append(Messages.LS);
		
		sb.append("startTime: ").append(DateUtils.format(this.startTime)).append(Messages.LS);
		if (this.endTime!=null) sb.append("endTime: ").append(DateUtils.format(this.endTime)).append(Messages.LS);	
		
		return sb.toString();
		
	}


	/**
	 * método que retorna el usuario que realiza el servicio
	 * @return
	 */
	public User getUser() {
		return this.user;
	}
	
}

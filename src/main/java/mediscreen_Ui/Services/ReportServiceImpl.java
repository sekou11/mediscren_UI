package mediscreen_Ui.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mediscreen_Ui.Proxies.ReportProxy;
@Service
public class ReportServiceImpl implements ReportProxy {
	 @Autowired
	private ReportProxy reportProxy;

	@Override
	public String getReportByPatient(int id) {
	
		return reportProxy.getReportByPatient(id);
	}

}

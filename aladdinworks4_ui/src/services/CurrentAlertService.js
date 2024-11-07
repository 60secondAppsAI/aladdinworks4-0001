import http from "../http-common"; 

class CurrentAlertService {
  getAllCurrentAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/currentAlert/currentAlerts`, searchDTO);
  }

  get(currentAlertId) {
    return this.getRequest(`/currentAlert/${currentAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/currentAlert?field=${matchData}`, null);
  }

  addCurrentAlert(data) {
    return http.post("/currentAlert/addCurrentAlert", data);
  }

  update(data) {
  	return http.post("/currentAlert/updateCurrentAlert", data);
  }
  
  uploadImage(data,currentAlertId) {
  	return http.postForm("/currentAlert/uploadImage/"+currentAlertId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CurrentAlertService();

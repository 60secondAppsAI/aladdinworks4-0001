import http from "../http-common"; 

class SwitchDeviceService {
  getAllSwitchDevices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/switchDevice/switchDevices`, searchDTO);
  }

  get(switchDeviceId) {
    return this.getRequest(`/switchDevice/${switchDeviceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/switchDevice?field=${matchData}`, null);
  }

  addSwitchDevice(data) {
    return http.post("/switchDevice/addSwitchDevice", data);
  }

  update(data) {
  	return http.post("/switchDevice/updateSwitchDevice", data);
  }
  
  uploadImage(data,switchDeviceId) {
  	return http.postForm("/switchDevice/uploadImage/"+switchDeviceId, data);
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

export default new SwitchDeviceService();

import http from "../http-common"; 

class GeneratorUnitService {
  getAllGeneratorUnits(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/generatorUnit/generatorUnits`, searchDTO);
  }

  get(generatorUnitId) {
    return this.getRequest(`/generatorUnit/${generatorUnitId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/generatorUnit?field=${matchData}`, null);
  }

  addGeneratorUnit(data) {
    return http.post("/generatorUnit/addGeneratorUnit", data);
  }

  update(data) {
  	return http.post("/generatorUnit/updateGeneratorUnit", data);
  }
  
  uploadImage(data,generatorUnitId) {
  	return http.postForm("/generatorUnit/uploadImage/"+generatorUnitId, data);
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

export default new GeneratorUnitService();

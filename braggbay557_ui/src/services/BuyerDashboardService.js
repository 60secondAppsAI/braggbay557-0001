import http from "../http-common"; 

class BuyerDashboardService {
  getAllBuyerDashboards(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/buyerDashboard/buyerDashboards`, searchDTO);
  }

  get(buyerDashboardId) {
    return this.getRequest(`/buyerDashboard/${buyerDashboardId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/buyerDashboard?field=${matchData}`, null);
  }

  addBuyerDashboard(data) {
    return http.post("/buyerDashboard/addBuyerDashboard", data);
  }

  update(data) {
  	return http.post("/buyerDashboard/updateBuyerDashboard", data);
  }
  
  uploadImage(data,buyerDashboardId) {
  	return http.postForm("/buyerDashboard/uploadImage/"+buyerDashboardId, data);
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

export default new BuyerDashboardService();

import http from "../http-common"; 

class SellerDashboardService {
  getAllSellerDashboards(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/sellerDashboard/sellerDashboards`, searchDTO);
  }

  get(sellerDashboardId) {
    return this.getRequest(`/sellerDashboard/${sellerDashboardId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/sellerDashboard?field=${matchData}`, null);
  }

  addSellerDashboard(data) {
    return http.post("/sellerDashboard/addSellerDashboard", data);
  }

  update(data) {
  	return http.post("/sellerDashboard/updateSellerDashboard", data);
  }
  
  uploadImage(data,sellerDashboardId) {
  	return http.postForm("/sellerDashboard/uploadImage/"+sellerDashboardId, data);
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

export default new SellerDashboardService();

import http from "../http-common"; 

class WatchlistItemService {
  getAllWatchlistItems(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/watchlistItem/watchlistItems`, searchDTO);
  }

  get(watchlistItemId) {
    return this.getRequest(`/watchlistItem/${watchlistItemId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/watchlistItem?field=${matchData}`, null);
  }

  addWatchlistItem(data) {
    return http.post("/watchlistItem/addWatchlistItem", data);
  }

  update(data) {
  	return http.post("/watchlistItem/updateWatchlistItem", data);
  }
  
  uploadImage(data,watchlistItemId) {
  	return http.postForm("/watchlistItem/uploadImage/"+watchlistItemId, data);
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

export default new WatchlistItemService();

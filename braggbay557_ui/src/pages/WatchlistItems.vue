<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <watchlistItem-table
            v-if="watchlistItems && watchlistItems.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:watchlistItems="watchlistItems"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-watchlist-items="getAllWatchlistItems"
             >

            </watchlistItem-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import WatchlistItemTable from "@/components/WatchlistItemTable";
import WatchlistItemService from "../services/WatchlistItemService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WatchlistItemTable,
  },
  data() {
    return {
      watchlistItems: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllWatchlistItems(sortBy='watchlistItemId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WatchlistItemService.getAllWatchlistItems(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.watchlistItems.length) {
					this.watchlistItems = response.data.watchlistItems;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching watchlistItems:", error);
        }
        
      } catch (error) {
        console.error("Error fetching watchlistItem details:", error);
      }
    },
  },
  mounted() {
    this.getAllWatchlistItems();
  },
  created() {
    this.$root.$on('searchQueryForWatchlistItemsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWatchlistItems();
    })
  }
};
</script>
<style></style>

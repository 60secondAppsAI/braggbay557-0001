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
            <buyerDashboard-table
            v-if="buyerDashboards && buyerDashboards.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:buyerDashboards="buyerDashboards"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-buyer-dashboards="getAllBuyerDashboards"
             >

            </buyerDashboard-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BuyerDashboardTable from "@/components/BuyerDashboardTable";
import BuyerDashboardService from "../services/BuyerDashboardService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BuyerDashboardTable,
  },
  data() {
    return {
      buyerDashboards: [],
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
    async getAllBuyerDashboards(sortBy='buyerDashboardId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BuyerDashboardService.getAllBuyerDashboards(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.buyerDashboards.length) {
					this.buyerDashboards = response.data.buyerDashboards;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching buyerDashboards:", error);
        }
        
      } catch (error) {
        console.error("Error fetching buyerDashboard details:", error);
      }
    },
  },
  mounted() {
    this.getAllBuyerDashboards();
  },
  created() {
    this.$root.$on('searchQueryForBuyerDashboardsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBuyerDashboards();
    })
  }
};
</script>
<style></style>

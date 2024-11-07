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
            <sellerDashboard-table
            v-if="sellerDashboards && sellerDashboards.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:sellerDashboards="sellerDashboards"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seller-dashboards="getAllSellerDashboards"
             >

            </sellerDashboard-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SellerDashboardTable from "@/components/SellerDashboardTable";
import SellerDashboardService from "../services/SellerDashboardService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SellerDashboardTable,
  },
  data() {
    return {
      sellerDashboards: [],
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
    async getAllSellerDashboards(sortBy='sellerDashboardId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SellerDashboardService.getAllSellerDashboards(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.sellerDashboards.length) {
					this.sellerDashboards = response.data.sellerDashboards;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching sellerDashboards:", error);
        }
        
      } catch (error) {
        console.error("Error fetching sellerDashboard details:", error);
      }
    },
  },
  mounted() {
    this.getAllSellerDashboards();
  },
  created() {
    this.$root.$on('searchQueryForSellerDashboardsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSellerDashboards();
    })
  }
};
</script>
<style></style>

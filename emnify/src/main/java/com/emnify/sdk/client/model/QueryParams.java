/*-
 * #%L
 * EMnify Java SDK
 * %%
 * Copyright (C) 2021 EMnify
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package com.emnify.sdk.client.model;

public class QueryParams {

    public static Builder builder() {
        return new Builder();
    }

    private QueryParams() {
    }

    private String filter;
    private String sort;
    private Integer page;
    private Integer perPage;

    public String getFilter() {
        return filter;
    }

    public String getSort() {
        return sort;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public static class Builder {
        private String filter;
        private String sort;
        private int page;
        private int perPage;

        /**
         * @param filter Filter parameter in &#x60;&lt;filter&gt;:&lt;value&gt;&#x60; format.
         *               Expects comma separated list of filtering criteria out of the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
         */
        public Builder setFilter(String filter) {
            this.filter = filter;
            return this;
        }

        /**
         * @param sort Sort properties in a comma separated list from the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;id&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
         */
        public Builder setSort(String sort) {
            this.sort = sort;
            return this;
        }

        public Builder setPage(int page) {
            this.page = page;
            return this;
        }

        public Builder setPerPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public QueryParams build() {
            QueryParams options = new QueryParams();

            options.filter = filter;
            options.sort = sort;
            options.page = page > 0 ? page : null;
            options.perPage = perPage > 0 ? perPage : null;

            return options;
        }
    }
}

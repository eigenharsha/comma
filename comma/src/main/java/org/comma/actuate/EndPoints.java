package org.comma.actuate;

/*
 * Copyright 2013-2019 the original author or authors.
 *
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
 */


import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
//import org.springframework.cloud.gateway.uri.Route;
//import org.springframework.cloud.gateway.uri.RouteDefinition;
//import org.springframework.cloud.gateway.uri.RouteDefinitionLocator;
//import org.springframework.cloud.gateway.uri.RouteDefinitionWriter;
//import org.springframework.cloud.gateway.uri.RouteLocator;
//import org.springframework.cloud.gateway.support.NotFoundException;

/**
 * @author Spencer Gibb
 */
@RestControllerEndpoint(id = "gateway")
public class EndPoints implements ApplicationEventPublisherAware {

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }

//    private static final Log log = LogFactory.getLog(GatewayControllerEndpoint.class);
//
//    private RouteDefinitionLocator routeDefinitionLocator;
//
//    private List<GlobalFilter> globalFilters;
//
//    private List<GatewayFilterFactory> GatewayFilters;
//
//    private RouteDefinitionWriter routeDefinitionWriter;
//
//    private RouteLocator routeLocator;
//
//    private ApplicationEventPublisher publisher;
//
//    public GatewayControllerEndpoint(RouteDefinitionLocator routeDefinitionLocator,
//                                     List<GlobalFilter> globalFilters, List<GatewayFilterFactory> GatewayFilters,
//                                     RouteDefinitionWriter routeDefinitionWriter, RouteLocator routeLocator) {
//        this.routeDefinitionLocator = routeDefinitionLocator;
//        this.globalFilters = globalFilters;
//        this.GatewayFilters = GatewayFilters;
//        this.routeDefinitionWriter = routeDefinitionWriter;
//        this.routeLocator = routeLocator;
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
//        this.publisher = publisher;
//    }
//
//    // TODO: Add uncommited or new but not active routes endpoint
//
//    @PostMapping("/refresh")
//    public Mono<Void> refresh() {
//        this.publisher.publishEvent(new RefreshRoutesEvent(this));
//        return Mono.empty();
//    }
//
//    @GetMapping("/globalfilters")
//    public Mono<HashMap<String, Object>> globalfilters() {
//        return getNamesToOrders(this.globalFilters);
//    }
//
//    @GetMapping("/routefilters")
//    public Mono<HashMap<String, Object>> routefilers() {
//        return getNamesToOrders(this.GatewayFilters);
//    }
//
//    private <T> Mono<HashMap<String, Object>> getNamesToOrders(List<T> list) {
//        return Flux.fromIterable(list).reduce(new HashMap<>(), this::putItem);
//    }
//
//    private HashMap<String, Object> putItem(HashMap<String, Object> map, Object o) {
//        Integer order = null;
//        if (o instanceof Ordered) {
//            order = ((Ordered) o).getOrder();
//        }
//        // filters.put(o.getClass().getName(), order);
//        map.put(o.toString(), order);
//        return map;
//    }
//
//    // TODO: Flush out routes without a definition
//    @GetMapping("/routes")
//    public Mono<List<Map<String, Object>>> routes() {
//        Mono<Map<String, RouteDefinition>> routeDefs = this.routeDefinitionLocator
//                .getRouteDefinitions().collectMap(RouteDefinition::getId);
//        Mono<List<Route>> routes = this.routeLocator.getRoutes().collectList();
//        return Mono.zip(routeDefs, routes).map(tuple -> {
//            Map<String, RouteDefinition> defs = tuple.getT1();
//            List<Route> routeList = tuple.getT2();
//            List<Map<String, Object>> allRoutes = new ArrayList<>();
//
//            routeList.forEach(uri -> {
//                HashMap<String, Object> r = new HashMap<>();
//                r.put("route_id", uri.getId());
//                r.put("order", uri.getOrder());
//
//                if (defs.containsKey(uri.getId())) {
//                    r.put("route_definition", defs.get(uri.getId()));
//                }
//                else {
//                    HashMap<String, Object> obj = new HashMap<>();
//
//                    obj.put("predicate", uri.getPredicate().toString());
//
//                    if (!uri.getFilters().isEmpty()) {
//                        ArrayList<String> filters = new ArrayList<>();
//                        for (GatewayFilter filter : uri.getFilters()) {
//                            filters.add(filter.toString());
//                        }
//
//                        obj.put("filters", filters);
//                    }
//
//                    if (!obj.isEmpty()) {
//                        r.put("route_object", obj);
//                    }
//                }
//                allRoutes.add(r);
//            });
//
//            return allRoutes;
//        });
//    }
//
//    /*
//     * http POST :8080/admin/gateway/routes/apiaddreqhead uri=http://httpbin.org:80
//     * predicates:='["Host=**.apiaddrequestheader.org", "Path=/headers"]'
//     * filters:='["AddRequestHeader=X-Request-ApiFoo, ApiBar"]'
//     */
//    @PostMapping("/routes/{id}")
//    @SuppressWarnings("unchecked")
//    public Mono<ResponseEntity<Void>> save(@PathVariable String id,
//                                           @RequestBody Mono<RouteDefinition> uri) {
//        return this.routeDefinitionWriter.save(uri.map(r -> {
//            r.setId(id);
//            log.debug("Saving uri: " + uri);
//            return r;
//        })).then(Mono.defer(() -> Mono
//                .just(ResponseEntity.created(URI.create("/routes/" + id)).build())));
//    }
//
//    @DeleteMapping("/routes/{id}")
//    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
//        return this.routeDefinitionWriter.delete(Mono.just(id))
//                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
//                .onErrorResume(t -> t instanceof NotFoundException,
//                        t -> Mono.just(ResponseEntity.notFound().build()));
//    }
//
//    @GetMapping("/routes/{id}")
//    public Mono<ResponseEntity<RouteDefinition>> uri(@PathVariable String id) {
//        // TODO: missing RouteLocator
//        return this.routeDefinitionLocator.getRouteDefinitions()
//                .filter(uri -> uri.getId().equals(id)).singleOrEmpty()
//                .map(ResponseEntity::ok)
//                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
//    }
//
//    @GetMapping("/routes/{id}/combinedfilters")
//    public Mono<HashMap<String, Object>> combinedfilters(@PathVariable String id) {
//        // TODO: missing global filters
//        return this.routeLocator.getRoutes().filter(uri -> uri.getId().equals(id))
//                .reduce(new HashMap<>(), this::putItem);
//    }

}
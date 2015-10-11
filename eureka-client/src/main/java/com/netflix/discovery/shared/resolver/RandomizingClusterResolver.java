/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.discovery.shared.resolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Randomize cluster server list using local server name hash code as a seed for random number
 * generator.
 *
 * @author Tomasz Bak
 */
public class RandomizingClusterResolver<T extends EurekaEndpoint> implements ClusterResolver<T> {

    private final List<T> eurekaEndpoints;
    private final String region;

    public RandomizingClusterResolver(ClusterResolver<T> delegate) {
        this.eurekaEndpoints = ResolverUtils.randomize(new ArrayList<>(delegate.getClusterEndpoints()));
        this.region = delegate.getRegion();
    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public List<T> getClusterEndpoints() {
        return eurekaEndpoints;
    }
}
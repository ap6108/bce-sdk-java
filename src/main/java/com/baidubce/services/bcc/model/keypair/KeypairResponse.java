/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.keypair;

import com.baidubce.model.AbstractBceResponse;

/**
 * The class contains keypair information
 */
public class KeypairResponse extends AbstractBceResponse {

    /**
     * The keypair model
     */
    private KeypairModel keypair;

    public KeypairModel getKeypair() {
        return keypair;
    }

    public void setKeypair(KeypairModel keypair) {
        this.keypair = keypair;
    }

    @Override
    public String toString() {
        return "KeypairResponse{keypair=" + keypair + "}";
    }
}
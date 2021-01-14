/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.mms.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * MMS base response.
 */
public class MmsBaseResponse extends AbstractBceResponse {

    private String status;
    private String taskId;
    private MmsError error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public MmsError getError() {
        return error;
    }

    public void setError(MmsError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "MmsBaseResponse{" +
                "status='" + status + '\'' +
                ", taskId='" + taskId + '\'' +
                ", error=" + error +
                ", metadata=" + metadata +
                '}';
    }
}

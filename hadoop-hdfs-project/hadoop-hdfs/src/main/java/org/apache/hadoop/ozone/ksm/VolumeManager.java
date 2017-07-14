/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership.  The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.hadoop.ozone.ksm;

import org.apache.hadoop.ksm.helpers.KsmVolumeArgs;
import org.apache.hadoop.ozone.protocol.proto
    .KeySpaceManagerProtocolProtos.OzoneAclInfo;

import java.io.IOException;
import java.util.List;

/**
 * KSM volume manager interface.
 */
public interface VolumeManager {

  /**
   * Create a new volume.
   * @param args - Volume args to create a volume
   */
  void createVolume(KsmVolumeArgs args) throws IOException;

  /**
   * Changes the owner of a volume.
   *
   * @param volume - Name of the volume.
   * @param owner - Name of the owner.
   * @throws IOException
   */
  void setOwner(String volume, String owner) throws IOException;

  /**
   * Changes the Quota on a volume.
   *
   * @param volume - Name of the volume.
   * @param quota - Quota in bytes.
   * @throws IOException
   */
  void setQuota(String volume, long quota) throws IOException;

  /**
   * Gets the volume information.
   * @param volume - Volume name.
   * @return VolumeArgs or exception is thrown.
   * @throws IOException
   */
  KsmVolumeArgs getVolumeInfo(String volume) throws IOException;

  /**
   * Deletes an existing empty volume.
   *
   * @param volume - Name of the volume.
   * @throws IOException
   */
  void deleteVolume(String volume) throws IOException;

  /**
   * Checks if the specified user with a role can access this volume.
   *
   * @param volume - volume
   * @param userAcl - user acl which needs to be checked for access
   * @return true if the user has access for the volume, false otherwise
   * @throws IOException
   */
  boolean checkVolumeAccess(String volume, OzoneAclInfo userAcl)
      throws IOException;

  /**
   * Returns a list of volumes owned by a given user; if user is null,
   * returns all volumes.
   *
   * @param userName
   *   volume owner
   * @param prefix
   *   the volume prefix used to filter the listing result.
   * @param startKey
   *   the start volume name determines where to start listing from,
   *   this key is excluded from the result.
   * @param maxKeys
   *   the maximum number of volumes to return.
   * @return a list of {@link KsmVolumeArgs}
   * @throws IOException
   */
  List<KsmVolumeArgs> listVolumes(String userName, String prefix,
      String startKey, int maxKeys) throws IOException;
}
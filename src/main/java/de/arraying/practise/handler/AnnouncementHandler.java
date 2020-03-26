package de.arraying.practise.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2018 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public final class AnnouncementHandler {

    private int index = 0;
    private final List<String> messages = new ArrayList<>();

    /**
     * Loads the messages.
     * @param messages The message.
     */
    public void load(List<String> messages) {
        this.messages.addAll(messages);
    }

    /**
     * Gets the next message.
     * @return The next message.
     */
    public synchronized String next() {
        if(++index >= messages.size()) {
            index = 0;
        }
        return messages.get(index);
    }

}

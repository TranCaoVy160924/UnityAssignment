using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;

public class SoundPlayer : MonoBehaviour
{
    [SerializeField] AudioSource music;

    void Start()
    {
        //if (OnOffMusic.isOn == false)
        //{
        //    music.Stop();
        //}
    }
}
